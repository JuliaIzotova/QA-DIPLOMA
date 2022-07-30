package ru.netology.test.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;

public class ApiTest {
    DataHelper.CardInfo approvedCardInfo = DataHelper.getApprovedCard();
    DataHelper.CardInfo declinedCardInfo = DataHelper.getDeclinedCard();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured());
    }

    @DisplayName("Запрос на покупку по карте со статусом APPROVED")
    @Test
    void shouldApprovePayment() {
       ApiHelper.payDebitCard((approvedCardInfo));
    }

    @DisplayName("Запрос на кредит по карте со статусом APPROVED")
    @Test
    void shouldApproveCredit() {
        ApiHelper.payCreditCard(approvedCardInfo);
    }

    @DisplayName("Запрос на покупку по карте со статусом DECLINED")
    @Test
    void shouldDeclinePayment() {
        ApiHelper.createPaymentError(declinedCardInfo);
    }

    @DisplayName("Запрос на кредит по карте со статусом DECLINED")
    @Test
    void shouldDeclineCredit() {
        ApiHelper.createCreditError(declinedCardInfo);
    }
}
