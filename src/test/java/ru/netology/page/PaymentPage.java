package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.page.MainPage.*;

public class PaymentPage {


    public void payDebitCard() {
        buttonBuy.click();
        buttonDebit.shouldBe(visible);
    }

    public void buyCreditCard() {
        buttonBuyWithCredit.click();
        buttonCredit.shouldBe(visible);
    }

    private final SelenideElement cardNumberField = $x("//input[@placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $x("//input[@placeholder='08']");
    private final SelenideElement yearField = $x("//input[@placeholder='22']");
    private final SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement cvcField = $x("//input[@placeholder='999']");
    private SelenideElement buttonContinue = $x("//span[text()='Продолжить']//ancestor::button");


    //    ошибки полей
    private SelenideElement fieldCardNumberError = $x("//*[text()='Номер карты']/..//*[@class='input__sub']");
    private SelenideElement fieldMonthError = $x("//*[text()='Месяц']/..//*[@class='input__sub']");
    private SelenideElement fieldYearError = $x("//*[text()='Год']/..//*[@class='input__sub']");
    private SelenideElement fieldOwnerError = $x("//*[text()='Владелец']/..//*[@class='input__sub']");
    private SelenideElement fieldCvcError = $x("//*[text()='CVC/CVV']/..//*[@class='input__sub']");

    private SelenideElement notificationApproved = $x("//div[contains(@class, 'notification_status_ok')]");
    private SelenideElement notificationError = $x("//div[contains(@class, 'notification_status_error')]");

    //    продолжить
    public void pressButtonForContinue() {
        buttonContinue.click();
    }

    public void sendingValidData (DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getHolder());
        cvcField.setValue(info.getCvc());
        buttonContinue.click();
    }

    public void sendingNotValidData (DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getHolder());
        cvcField.setValue(info.getCvc());
        buttonContinue.click();
    }

    public void sendingValidDataWithFieldCardNumberError () {
        fieldCardNumberError.shouldBe(visible);
        fieldMonthError.shouldBe(hidden);
        fieldYearError.shouldBe(hidden);
        fieldOwnerError.shouldBe(hidden);
        fieldCvcError.shouldBe(hidden);
    }

    public void sendingValidDataWithFakerCardNumber () {
        fieldMonthError.shouldBe(hidden);
        fieldYearError.shouldBe(hidden);
        fieldOwnerError.shouldBe(hidden);
        fieldCvcError.shouldBe(hidden);
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void sendingValidDataWithFieldMonthError () {
        fieldCardNumberError.shouldBe(hidden);
        fieldMonthError.shouldBe(visible);
        fieldYearError.shouldBe(hidden);
        fieldOwnerError.shouldBe(hidden);
        fieldCvcError.shouldBe(hidden);
    }

    public void sendingValidDataWithFieldYearError () {
        fieldCardNumberError.shouldBe(hidden);
        fieldMonthError.shouldBe(hidden);
        fieldYearError.shouldBe(visible);
        fieldOwnerError.shouldBe(hidden);
        fieldCvcError.shouldBe(hidden);
    }

    public void sendingEmptyNameValidData (DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cvcField.setValue(info.getCvc());
        buttonContinue.click();
    }

    public void sendingValidDataWithFieldNameError () {
        fieldCardNumberError.shouldBe(hidden);
        fieldMonthError.shouldBe(hidden);
        fieldYearError.shouldBe(hidden);
        fieldOwnerError.shouldBe(visible);
        fieldCvcError.shouldBe(hidden);
    }

    public void sendingValidDataWithFieldCVVError () {
        fieldCardNumberError.shouldBe(hidden);
        fieldMonthError.shouldBe(hidden);
        fieldYearError.shouldBe(hidden);
        fieldOwnerError.shouldBe(hidden);
        fieldCvcError.shouldBe(visible);
    }

    public void emptyForm() {
        buttonContinue.click();
        fieldCardNumberError.shouldBe(visible);
        fieldMonthError.shouldBe(visible);
        fieldYearError.shouldBe(visible);
        fieldOwnerError.shouldBe(visible);
        fieldCvcError.shouldBe(visible);
    }

    public void bankApproved() {

        notificationApproved.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void bankDeclined() {

        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

}















