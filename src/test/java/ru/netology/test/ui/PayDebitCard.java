package ru.netology.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;


public class PayDebitCard {
    PaymentPage paymentPage = new PaymentPage();


    @BeforeAll
    public static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {

        open("http://localhost:8080");
    }

    @AfterEach
    void cleanDB() {

        SQLHelper.databaseCleanUp();
    }

    @AfterAll
    public static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    @SneakyThrows
    @DisplayName("Покупка валидной картой")
    public void shouldPayDebitValidCard() {
        paymentPage.payDebitCard();
        var info = getApprovedCard();
        paymentPage.sendingValidData(info);
        paymentPage.bankApproved();
        var expected = DataHelper.getStatusFirstCard();
        var paymentInfo = SQLHelper.getPaymentInfo();
        var orderInfo = SQLHelper.getOrderInfo();
        var expectedAmount = "45000";
        assertEquals(expected, getPaymentInfo().getStatus());
        assertEquals(paymentInfo.getTransaction_id(), orderInfo.getPayment_id());
        assertEquals(expectedAmount, paymentInfo.getAmount());
    }


    @Test
    @SneakyThrows
    @DisplayName("Покупка дебетовой невалидной картой")
    void shouldPayDebitDeclinedCard() {
        paymentPage.payDebitCard();
        var info = DataHelper.getDeclinedCard();
        paymentPage.sendingNotValidData(info);
        paymentPage.bankDeclined();
        var paymentStatus = getPaymentInfo();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Покупка дебетовой картой без заполнения полей")
    void shouldEmptyFormDebitCard() {
        paymentPage.payDebitCard();
        paymentPage.pressButtonForContinue();
        paymentPage.emptyForm();

    }

    @Test
    @DisplayName("Покупка дебетовой картой без заполнения поля карты, остальные поля - валидные данные")
    void shouldEmptyFieldCardFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getEmptyCardNumber();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой при заполнения поля карты одной цифрой, остальные поля - валидные данные")
    public void shouldOneNumberInFieldCardFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getOneNumberCardNumber();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой при заполнения поля карты 15 цифрами, остальные поля - валидные данные")
    public void shouldFifteenNumberInFieldCardNumberFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getFifteenNumberCardNumber();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    @Test
    @DisplayName("Покупка картой не из БД, остальные поля - валидные данные")
    public void shouldFakerCardNumberFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getFakerNumberCardNumber();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFakerCardNumber();
    }


    @Test
    @DisplayName("Покупка дебетовой картой без заполнения поля месяц, остальные поля - валидные данные")
    public void shouldEmptyFieldMonthFormDebit() {
        paymentPage.payDebitCard();
        var info = getEmptyMonth();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой c заполнением поля месяц одной цифрой, остальные поля - валидные данные")
    public void shouldOneNumberInFieldMonthFormDebit() {
        paymentPage.payDebitCard();
        var info = getOneNumberMonth();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: в поле месяц предыдущий от текущего, остальные поля -валидные данные")
    public void shouldFieldWithPreviousMonthFormDebit() {
        paymentPage.payDebitCard();
        var info = getPreviousMonthInField();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: в поле месяц нулевой (не существующий) месяц" +
            " остальные поля - валидные данные")
    public void shouldFieldWithZeroMonthFormDebit() {
        paymentPage.payDebitCard();
        var info = getZeroMonthInField();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: в поле месяц тринадцатый (не существующий) месяц" +
            " остальные поля - валидные данные")
    public void shouldFieldWithThirteenMonthFormDebit() {
        paymentPage.payDebitCard();
        var info = getThirteenMonthInField();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой без заполнения поля год, остальные поля - валидные данные")
    public void shouldEmptyFieldYearFormDebit() {
        paymentPage.payDebitCard();
        var info = getEmptyYear();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: заполнение поля год, предыдущим годом от текущего" +
            " остальные поля - валидные данные")
    public void shouldPreviousYearFieldYearFormDebit() {
        paymentPage.payDebitCard();
        var info = getPreviousYearInField();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: заполнение поля год, на шесть лет больше чем текущий" +
            " остальные поля - валидные данные")
    public void shouldPlusSixYearFieldYearFormDebit() {
        paymentPage.payDebitCard();
        var info = getPlusSixYearInField();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: поле владелец пустое, остальные - валидные данные")
    public void shouldEmptyFieldNameFormDebit() {
        paymentPage.payDebitCard();
        var info = getApprovedCard();
        paymentPage.sendingEmptyNameValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }


    @Test
    @DisplayName("Покупка дебетовой картой: заполнение поля владелец спец. символами" +
            " остальные поля - валидные данные")
    public void shouldSpecialSymbolInFieldNameFormDebit() {
        paymentPage.payDebitCard();
        var info = getSpecialSymbolInFieldName();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: заполнение  поля владелец цифрами" +
            " остальные поля - валидные данные")
    public void shouldNumberInFieldNameFormDebit() {
        paymentPage.payDebitCard();
        var info = getNumberInFieldName();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: заполнение поля владелец рус буквами" +
            " остальные поля - валидные данные")
    public void shouldEnglishNameInFieldNameFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getRusName();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: поле владелец только фамилия, остальные поля - валидные данные")
    public void shouldOnlySurnameFormDebit() {
        paymentPage.payDebitCard();
        var info = DataHelper.getOnlySurnameInFieldName();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: поле CVV пустое" +
            " остальные поля - валидные данные")
    public void shouldEmptyCVVInFieldCVVFormDebit() {
        paymentPage.payDebitCard();
        var info = getEmptyCVVInFieldCVV();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: поле CVV одно число" +
            " остальные поля - валидные данные")
    public void shouldOneNumberInFieldCVVFormDebit() {
        paymentPage.payDebitCard();
        var info = getOneNumberInFieldCVV();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    @Test
    @DisplayName("Покупка дебетовой картой: поле CVV двумя числами" +
            " остальные поля - валидные данные")
    public void shouldTwoNumberInFieldCVVАFormDebit() {
        paymentPage.payDebitCard();
        var info = getOTwoNumberInFieldCVV();
        paymentPage.sendingValidData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }
}
























