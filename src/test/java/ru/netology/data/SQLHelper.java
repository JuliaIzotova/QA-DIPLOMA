package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.Timestamp;

public class SQLHelper {
    private static final String datasource = System.getProperty("datasource");

    @SneakyThrows
    public static void databaseCleanUp() {
        var runner = new QueryRunner();
        var deleteFromOrder = "DELETE FROM order_entity;";
        var deleteFromCredit = "DELETE FROM credit_request_entity;";
        var deleteFromPayment = "DELETE FROM payment_entity;";

        try (var connection = DriverManager.getConnection(
                datasource, "app", "pass")) {
            runner.update(connection, deleteFromOrder);
            runner.update(connection, deleteFromCredit);
            runner.update(connection, deleteFromPayment);
        }
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
        var runner = new QueryRunner();
        var creditRequestInfo = "SELECT * FROM credit_request_entity ORDER BY created DESC;";

        try (var connection = DriverManager.getConnection(
                datasource, "app", "pass")) {
            return runner.query(connection, creditRequestInfo, new BeanHandler<>(CreditRequestEntity.class));
        }
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
        var runner = new QueryRunner();
        var paymentInfo = "SELECT * FROM payment_entity ORDER BY created DESC;";
        ResultSetHandler<PaymentEntity> resultSetHandler = new BeanHandler<>(PaymentEntity.class);
        var connection = DriverManager.getConnection(datasource, "app", "pass");
        {
            return runner.query(connection, paymentInfo, resultSetHandler);
        }
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
        var runner = new QueryRunner();
        var orderInfo = "SELECT * FROM order_entity ORDER BY created DESC;";
        ResultSetHandler<OrderEntity> resultSetHandler = new BeanHandler<>(OrderEntity.class);
        var connection = DriverManager.getConnection(datasource, "app", "pass");
        {
            return runner.query(connection, orderInfo, resultSetHandler);
        }
    }
}



