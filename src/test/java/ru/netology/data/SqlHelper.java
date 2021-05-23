package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.databaseentities.CreditRequestEntity;
import ru.netology.databaseentities.OrderEntity;
import ru.netology.databaseentities.PaymentEntity;

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

    public static String getCardStatusForPayment() {
        String statusQuery = "SELECT * FROM app.payment_entity";
        val runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            val cardStatus = runner.query
                    (connection, statusQuery, new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getCardStatusForCreditRequest() {
        String statusQuery = "SELECT * FROM app.credit_request_entity";
        val runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            val cardStatus = runner.query
                    (connection, statusQuery, new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getPaymentIdForCardPay() {
        val idQueryForCardPay = "SELECT * FROM app.order_entity";
        val runner = new QueryRunner();
        try (val connection = getConnection()) {
            val paymentId = runner.query
                    (connection, idQueryForCardPay, new BeanHandler<>(OrderEntity.class));
            return paymentId.getPayment_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPaymentIdForCreditRequest() {
        val idQueryForCreditRequest = "SELECT * FROM app.order_entity";
        val runner = new QueryRunner();
        try (val connection = getConnection()) {
            val creditId = runner.query
                    (connection, idQueryForCreditRequest, new BeanHandler<>(OrderEntity.class));
            return creditId.getCredit_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTransactionId() {
        val runner = new QueryRunner();
        String idTransactionQuery = "SELECT * FROM app.payment_entity";
        try (Connection connection = getConnection()) {
            val transactionId = runner.query
                    (connection, idTransactionQuery, new BeanHandler<>(PaymentEntity.class));
            return transactionId.getTransaction_id();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getAmountPayment() {
        val runner = new QueryRunner();
        String amountQuery = "SELECT * FROM app.payment_entity";
        try (Connection connection = getConnection()) {
            val transactionId = runner.query
                    (connection, amountQuery, new BeanHandler<>(PaymentEntity.class));
            return transactionId.getAmount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getBankId() {
        String bankIdQuery = "SELECT * FROM app.credit_request_entity";
        val runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            val bankId = runner.query
                    (connection, bankIdQuery, new BeanHandler<>(CreditRequestEntity.class));
            return bankId.getBank_id();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}