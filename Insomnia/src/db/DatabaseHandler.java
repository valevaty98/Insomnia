package db;

import objects.Book;
import objects.Status;
import objects.User;
import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        DatabaseHandler.user = user;
    }

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }


    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," +
                Const.USERS_SURNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_LOGIN +
                "," + Const.USERS_PASSWORD + ") VALUES" + "(?,?,?,?,?)";

        PreparedStatement insertStatement = getDbConnection().prepareStatement(insert);
        insertStatement.setString(1,user.getName());
        insertStatement.setString(2,user.getSurname());
        insertStatement.setString(3,user.getEmail());
        insertStatement.setString(4,user.getLogin());
        insertStatement.setString(5,user.getPassword());

        insertStatement.executeUpdate();

        this.user = user;
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet userSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN +
                "=? AND " + Const.USERS_PASSWORD + "=?";

        PreparedStatement selectStatement = getDbConnection().prepareStatement(select);
        selectStatement.setString(1,user.getLogin());
        selectStatement.setString(2,user.getPassword());

        userSet = selectStatement.executeQuery();
        if (userSet.next()) user.setId(String.valueOf(userSet.getInt(1)));
        this.user = user;
        return userSet;
    }

    public void addBookToUser (Book book) throws SQLException, ClassNotFoundException {
        String id = this.user.getId();
        String insert = "INSERT INTO " + Const.BOOK_TABLE + "(" + Const.BOOKS_USER_ID + "," + Const.BOOKS_STATUS + "," +
                Const.BOOKS_TITLE + "," + Const.BOOKS_AUTHOR + "," + Const.BOOKS_FROMDATE + "," + Const.BOOKS_TILLDATE +
                "," + Const.BOOKS_AUDIO + "," + Const.BOOKS_NOTES + "," + Const.BOOKS_GENRE + ") VALUES " + "(?,?,?,?,?,?,?,?,?)";

        PreparedStatement insertStatement = getDbConnection().prepareStatement(insert);
        insertStatement.setInt(1, Integer.valueOf(id));
        insertStatement.setString(2, book.getStatus().toString());
        insertStatement.setString(3, book.getTitle());
        insertStatement.setString(4, book.getAuthor());
        insertStatement.setString(5, book.getFromDate().toString());
        insertStatement.setString(6, book.getTillDate().toString());
        insertStatement.setString(7, Boolean.toString(book.isAudio()));
        insertStatement.setString(8, book.getNotes());
        insertStatement.setString(9, book.getGenre());

        insertStatement.executeUpdate();

        this.user = user;

        user.addBook(book);
    }

    public ResultSet getBooks(Status status) throws SQLException, ClassNotFoundException {
        ResultSet bookSet = null;

        String select = "SELECT " + Const.BOOKS_TITLE + "," + Const.BOOKS_AUTHOR + "," + Const.BOOKS_TILLDATE +
                " FROM " + Const.BOOK_TABLE + " WHERE " + Const.BOOKS_USER_ID + "=? AND " + Const.BOOKS_STATUS +
                "=?";

        PreparedStatement selectStatement = getDbConnection().prepareStatement(select);
        selectStatement.setInt(1, Integer.valueOf(user.getId()));
        selectStatement.setString(2, status.toString());

        bookSet = selectStatement.executeQuery();

        return bookSet;
    }

}
