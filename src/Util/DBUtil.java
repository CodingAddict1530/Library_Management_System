package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    // JDBC URL, Set last part (db name) to your database name
    private static final String JDBC_URL_POSTGRES = "jdbc:postgresql://localhost:5432/Library_Management_System";
    private static final String JDBC_URL_MYSQL = "jdbc:mysql://localhost:3306/Library_Management_System";
    private static final String JDBC_URL_SQLSERVER = "jdbc:sqlserver://localhost:1433;databaseName=yLibrary_Management_System";

    // Set USER and PASSWORD to your user and password
    private static final String JDBC_USER = "login1";
    private static final String JDBC_PASSWORD = "1234";

    // JDBC variables for opening and managing connection
    private static Connection conn;
    private static PreparedStatement pstmt;

    /**
     * Establishes a connection to the database based on the JDBC URL.
     *
     * @param jdbcUrl the JDBC URL of the database
     * @throws SQLException if a database access error occurs
     */
    public static void connect(String jdbcUrl) throws SQLException {

        // Check whether there is not already a connection
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(jdbcUrl, JDBC_USER, JDBC_PASSWORD);
        }
    }

    /**
     * Executes a query with optional parameters and returns a ResultSet. Use for SELECT
     *
     * @param sql the SQL query to execute
     * @param url the Url for the connection, should be one of the three predefined constants
     * @param params optional parameters for the query
     * @return the ResultSet object resulting from the query
     * @throws SQLException if a database access error occurs
     */
    public static ResultSet executeQuery(String sql, String url, Object... params) throws SQLException {
        connect(url);

        // Last 2 parameters make the ResultSet scrollable
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        // Set parameters, if any
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }

        return pstmt.executeQuery();
    }

    /**
     * Executes an update, insert, or delete SQL statement with optional parameters.
     *
     * @param sql the SQL statement to execute
     * @param url the Url for the connection, should be one of the three predefined constants
     * @param params optional parameters for the query
     * @return the number of rows affected
     * @throws SQLException if a database access error occurs
     */
    public static int executeUpdate(String sql, String url, Object... params) throws SQLException {
        connect(url);
        pstmt = conn.prepareStatement(sql);

        // Set parameters, if any
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }

        return pstmt.executeUpdate();
    }

    /**
     * Closes the connection and statement.
     */
    public static void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves the URL for connecting to PostgreSQL database
     *
     * @return the PostgreSQL Database URL
     */
    public static String getPostreSQLURL() {
        return JDBC_URL_POSTGRES;
    }

    /**
     * Retrieves the URL for connecting to MySQL database
     *
     * @return the MySQL Database URL
     */
    public static String getMySQLURL() {
        return JDBC_URL_MYSQL;
    }

    /**
     * Retrieves the URL for connecting to Ms SQL Server database
     *
     * @return the MS SQL Server Database URL
     */
    public static String getSQLServerURL() {
        return JDBC_URL_SQLSERVER;
    }
}
