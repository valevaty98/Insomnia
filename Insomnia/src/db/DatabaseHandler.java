package db;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, properties);

        return dbConnection;
    }
}
