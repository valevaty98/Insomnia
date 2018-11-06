package db;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import objects.User;

import java.sql.*;
import java.util.Properties;


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
}
