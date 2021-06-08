package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private static final String URL = System.getProperty("db.url");
    private static final String USER = System.getProperty("db.user");
    private static final String PASSWORD = System.getProperty("db.password");
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    public static void dropDataBase() {
        val runner = new QueryRunner();
        val order = "DELETE FROM app.order_entity";
        val payment = "DELETE FROM app.payment_entity";
        val creditRequest = "DELETE FROM app.credit_request_entity";

        try (val connection = getConnection()) {
            runner.update(connection, order);
            runner.update(connection, payment);
            runner.update(connection, creditRequest);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static String getPaymentStatus() throws SQLException {
        val codeSQL = "SELECT status FROM payment_entity";
        val runner = new QueryRunner();
        try (val connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            return runner.query(connection, codeSQL, new ScalarHandler<>());
        }
    }

    public static String getCreditStatus() throws SQLException {
        val codeSQL = "SELECT status FROM credit_request_entity";
        val runner = new QueryRunner();
        try (val connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            return runner.query(connection, codeSQL, new ScalarHandler<>());
        }
    }

    public static String getOrderInformation() throws SQLException {
        val codeSQL = "SELECT credit_id FROM order_entity";
        val runner = new QueryRunner();
        try (val connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return runner.query(connection, codeSQL, new ScalarHandler<>());
        }
    }
}