package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

public class SQL {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static void dropDataBase() {
        val runner = new QueryRunner();
        val creditRequest = "DELETE FROM credit_request_entity";
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";

        try (Connection connection = getConnection()) {
            runner.update(connection, creditRequest);
            runner.update(connection, order);
            runner.update(connection, payment);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static String getPaymentId() {
        val runner = new QueryRunner();
        String paymentId = "";
        String idPay = "SELECT payment_id FROM order_entity WHERE created=(SELECT MAX(created) FROM order_entity);";
        try (Connection connection = getConnection()) {
            paymentId = runner.query(connection, idPay, new ScalarHandler<>());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return paymentId;
    }

    public static String getAmountPayment(String paymentId) {
        val runner = new QueryRunner();
        String amount = "";
        String amountQuery = "SELECT amount FROM payment_entity WHERE transaction_id =?;";
        try (Connection connection = getConnection();
             PreparedStatement statusStatement = connection.prepareStatement(amountQuery)) {
            statusStatement.setString(1, paymentId);
            try (ResultSet resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    amount = resultSet.getString("amount");
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return amount;
    }

    public static String getPaymentStatus(String paymentId) {

        String status = "";
        String statusQuery = "SELECT status FROM payment_entity WHERE transaction_id =?;";
        try (Connection connection = getConnection();
             PreparedStatement statusStatement = connection.prepareStatement(statusQuery)) {
            statusStatement.setString(1, paymentId);
            try (ResultSet resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static String getPaymentStatusForCreditRequest(String paymentId) {

        String status = "";
        String statusQuery = "SELECT status FROM credit_request_entity WHERE bank_id =?;";
        try (Connection connection = getConnection();
             PreparedStatement statusStatement = connection.prepareStatement(statusQuery)) {
            statusStatement.setString(1, paymentId);
            try (ResultSet resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }
}
