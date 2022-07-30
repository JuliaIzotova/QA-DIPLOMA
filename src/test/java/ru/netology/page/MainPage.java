package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public static SelenideElement buttonBuy = $x("//span[text()='Купить']//ancestor::button");
    public static SelenideElement buttonBuyWithCredit = $x("//span[text()='Купить в кредит']//ancestor::button");

    //    заголовки появляющиеся после нажатия кнопок
    public static SelenideElement buttonDebit = $(withText("Оплата по карте"));
    public static SelenideElement buttonCredit = $(withText("Кредит по данным карты"));


    public void payDebitCard() {
        buttonBuy.click();
        buttonDebit.shouldBe(visible);
    }

    public void buyCreditCard() {
        buttonBuyWithCredit.click();
        buttonCredit.shouldBe(visible);
    }
}
