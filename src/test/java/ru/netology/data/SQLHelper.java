package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;

public class SQLHelper {
    private static Connection connection;
    public static QueryRunner runner;

    @SneakyThrows
    public static void start() {
        runner = new QueryRunner();
        connection = DriverManager.getConnection(System.getProperty("datasource"), "app", "pass");
    }


    @SneakyThrows
    public static void databaseCleanUp() {
        start();
        var deleteFromOrder = "DELETE FROM order_entity;";
        var deleteFromCredit = "DELETE FROM credit_request_entity;";
        var deleteFromPayment = "DELETE FROM payment_entity;";
        runner.update(connection, deleteFromOrder);
        runner.update(connection, deleteFromCredit);
        runner.update(connection, deleteFromPayment);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreditRequestEntity {
        private String id;
        private String bank_id;
        private Timestamp created;
        private String status;
    }

    @SneakyThrows
    public static CreditRequestEntity getCreditRequestInfo() {
        start();
        var creditRequestInfo = "SELECT * FROM credit_request_entity ORDER BY created DESC;";
        return runner.query(connection, creditRequestInfo, new BeanHandler<>(CreditRequestEntity.class));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public static class PaymentEntity {
        private String id;
        private int amount;
        private Timestamp created;
        private String status;
        private String transaction_id;
    }

    @SneakyThrows
    public static PaymentEntity getPaymentInfo() {
        start();
        var paymentInfo = "SELECT * FROM payment_entity ORDER BY created DESC;";
        return runner.query(connection, paymentInfo, new BeanHandler<>(PaymentEntity.class));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderEntity {
        private String id;
        private Timestamp created;
        private String credit_id;
        private String payment_id;
    }

    @SneakyThrows
    public static OrderEntity getOrderInfo() {
        start();
        var orderInfo = "SELECT * FROM order_entity ORDER BY created DESC;";
        return runner.query(connection, orderInfo, new BeanHandler<>(OrderEntity.class));
    }
}




