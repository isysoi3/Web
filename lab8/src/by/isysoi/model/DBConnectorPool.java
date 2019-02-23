package by.isysoi.model;

import by.isysoi.model.exception.DBConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * database connector singleton class
 *
 * @author Ilya Sysoi
 * @version 1.0.0
 */
public class DBConnectorPool {

    private static final String DB_PROPERTIES = "database/database.properties";
    private static DBConnectorPool instance;
    private static Logger logger = LogManager.getLogger("database_layer");
    private final int initConnectionsCount = 5;
    private BlockingQueue<Connection> connections;

    /**
     * init form properties file database pool of connections
     *
     * @throws DBConnectionException if properties file loading error
     */
    private DBConnectorPool() throws DBConnectionException {
        if (instance != null) {
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(DB_PROPERTIES));
            logger.info("Properties file loaded");
        } catch (IOException e) {
            throw new DBConnectionException("properties file not loaded");
        }
        String driver = properties.getProperty("driver");
        String DB_URL = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");


        try {
            Class.forName(driver);
            logger.info("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new DBConnectionException("Error loading driver!");
        }

        connections = new ArrayBlockingQueue<>(initConnectionsCount);
        try {
            for (int i = 0; i < initConnectionsCount; ++i) {
                Connection connection = DriverManager.getConnection(DB_URL, user, password);
                if (connection == null) {
                    throw new DBConnectionException("Driver type is not correct in URL " + DB_URL + ".");
                }
                connections.add(connection);
                logger.info("Connection " + i + " esteblished");
            }
        } catch (SQLException e) {
            throw new DBConnectionException("Failed to establish connection");
        }
        logger.info("DB pool of connections inited");
    }

    /**
     * return instance DBConnectorPool or create it
     *
     * @return instance of Singleton
     */
    public static synchronized DBConnectorPool getInstance() throws DBConnectionException {
        if (instance == null) {
            instance = new DBConnectorPool();
        }
        return instance;
    }

    /**
     * deinit database pool of connections
     *
     * @throws DBConnectionException if properties file loading error
     */
    public synchronized void deinitDBConnector() throws DBConnectionException {
        try {
            while (connections.size() > 0) {
                connections.take().close();
            }
        } catch (SQLException e) {
            throw new DBConnectionException("Could not close database connection ", e);
        } catch (InterruptedException e) {
            throw new DBConnectionException("Problem with concurrent queue", e);
        }
        logger.info("DB pool of connections deinited");
    }


    /**
     * take connection from pool
     *
     * @return connection
     */
    public synchronized Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return the connection to pool
     *
     * @param connection to add back to pool
     */
    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }

}
