package db;

import objects.User;
import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

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
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet userSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN +
                "=? AND " + Const.USERS_PASSWORD + "=?";

        PreparedStatement selectStatement = getDbConnection().prepareStatement(select);
        selectStatement.setString(1,user.getLogin());
        selectStatement.setString(2,user.getPassword());

        userSet = selectStatement.executeQuery();

        return userSet;
    }
}
