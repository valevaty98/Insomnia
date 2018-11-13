package db;

import objects.Book;
import objects.Quote;
import objects.Status;
import objects.User;

import java.sql.*;
import java.util.Random;

public class DatabaseHandler
{
    Connection dbConnection;
    private static User user;

    public DatabaseHandler() {
        try {
            setConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        DatabaseHandler.user = user;
    }

    public void setConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + Configs.dbHost + ":" + Configs.dbPort + "/" + Configs.dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, Configs.dbUser, Configs.dbPassword);
    }

    public Connection getConnection() {
        return dbConnection;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet userSet = null;
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," + Const.USERS_SURNAME +
                "," + Const.USERS_EMAIL + "," + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + ") VALUES" + "(?,?,?,?,?)";

        PreparedStatement insertStatement = getConnection().prepareStatement(insert);
        insertStatement.setString(1, user.getName());
        insertStatement.setString(2, user.getSurname());
        insertStatement.setString(3, user.getEmail());
        insertStatement.setString(4, user.getLogin());
        insertStatement.setString(5, user.getPassword());

        insertStatement.executeUpdate();

        String select = "SELECT " + Const.USERS_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN +
                "=? AND " + Const.USERS_PASSWORD + "=?";

        PreparedStatement selectStatement = getConnection().prepareStatement(select);
        selectStatement.setString(1, user.getLogin());
        selectStatement.setString(2, user.getPassword());

        userSet = selectStatement.executeQuery();

        if (userSet.next()) user.setId(String.valueOf(userSet.getInt(1)));
        this.user = user;
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet userSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " +
                Const.USERS_PASSWORD + "=?";

        PreparedStatement selectStatement = getConnection().prepareStatement(select);
        selectStatement.setString(1, user.getLogin());
        selectStatement.setString(2, user.getPassword());

        userSet = selectStatement.executeQuery();
        if (userSet.next()) {
            user.setId(String.valueOf(userSet.getInt(1)));
            this.user = user;
            return userSet;
        }
        return null;
    }

    public void addBookToUser(Book book) throws SQLException, ClassNotFoundException {
        String id = this.user.getId();
        String insert = "INSERT INTO " + Const.BOOK_TABLE + "(" + Const.BOOKS_USER_ID + "," + Const.BOOKS_STATUS + "," +
                Const.BOOKS_TITLE + "," + Const.BOOKS_AUTHOR + "," + Const.BOOKS_FROMDATE + "," + Const.BOOKS_TILLDATE +
                "," + Const.BOOKS_AUDIO + "," + Const.BOOKS_NOTES + "," + Const.BOOKS_GENRE + ") VALUES " + "(?,?,?,?,?,?,?,?,?)";

        PreparedStatement insertStatement = getConnection().prepareStatement(insert);
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

        user.addBook(book);
    }

    public void updateBookInDB(Book book) throws SQLException, ClassNotFoundException {
        int id_book = book.getId();
        String update = "UPDATE " + Const.BOOK_TABLE + " SET " + Const.BOOKS_STATUS + "=?, " + Const.BOOKS_TITLE + "=?, " +
                Const.BOOKS_AUTHOR + "=?, " + Const.BOOKS_FROMDATE + "=?, " + Const.BOOKS_TILLDATE + "=?, " +
                Const.BOOKS_AUDIO + "=?, " + Const.BOOKS_NOTES + "=?," + Const.BOOKS_GENRE + "=? WHERE " + Const.BOOKS_ID + "=?";

        PreparedStatement updateStatement = getConnection().prepareStatement(update);
        updateStatement.setString(1, book.getStatus().toString());
        updateStatement.setString(2, book.getTitle());
        updateStatement.setString(3, book.getAuthor());
        updateStatement.setString(4, book.getFromDate().toString());
        updateStatement.setString(5, book.getTillDate().toString());
        updateStatement.setString(6, Boolean.toString(book.isAudio()));
        updateStatement.setString(7, book.getNotes());
        updateStatement.setString(8, book.getGenre());
        updateStatement.setInt(9, id_book);

        updateStatement.executeUpdate();

        user.updateBook(book);
    }

    public ResultSet getBooks(Status status) throws SQLException, ClassNotFoundException {
        ResultSet bookSet = null;

        String select = "SELECT " + Const.BOOKS_ID + "," + Const.BOOKS_STATUS + "," + Const.BOOKS_TITLE + "," +
                Const.BOOKS_AUTHOR + "," + Const.BOOKS_TILLDATE + "," + Const.BOOKS_FROMDATE + "," + Const.BOOKS_AUDIO +
                "," + Const.BOOKS_GENRE + "," + Const.BOOKS_NOTES + " FROM " + Const.BOOK_TABLE + " WHERE " +
                Const.BOOKS_USER_ID + "=? AND " + Const.BOOKS_STATUS + "=?";

        PreparedStatement selectStatement = getConnection().prepareStatement(select);
        selectStatement.setInt(1, Integer.valueOf(user.getId()));
        selectStatement.setString(2, status.toString());

        bookSet = selectStatement.executeQuery();

        return bookSet;
    }

    public void deleteBookFromDB(Book book) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM " + Const.BOOK_TABLE + " WHERE " + Const.BOOKS_ID + "=?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, book.getId());

        preparedStatement.executeUpdate();

        user.deleteBook(book);
    }

    public Quote getRandomQuote() throws SQLException, ClassNotFoundException {
        Quote quote;
        int numberOfQuotes;

        String selectCount = "SELECT COUNT(*) FROM " + Const.QUOTE_TABLE;
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(selectCount);

        if (resultSet.next()) numberOfQuotes = resultSet.getInt(1);
        else numberOfQuotes = 0;

        Random random = new Random();

        String select = "SELECT " + Const.QUOTES_ID + "," + Const.QUOTES_AUTHOR + "," + Const.QUOTES_QUOTE + " FROM " +
                Const.QUOTE_TABLE + " WHERE " + Const.QUOTES_ID + "=?";

        PreparedStatement selectStatement = getConnection().prepareStatement(select);
        selectStatement.setInt(1, random.nextInt(numberOfQuotes) + 1);

        resultSet = selectStatement.executeQuery();

        if(resultSet.next()) quote = new Quote(resultSet.getInt(Const.QUOTES_ID), resultSet.getString(Const.QUOTES_QUOTE),
                resultSet.getString(Const.QUOTES_AUTHOR));
        else quote = new Quote(0, "Error!", "Error!");
        return quote;
    }
}
