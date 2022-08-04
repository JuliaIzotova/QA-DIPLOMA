package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {


    private static Faker faker = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private static String approvedCard = "4444 4444 4444 4441";
    private static String declinedCard = "4444 4444 4444 4442";


    private DataHelper() {
    }


    public static String getStatusFirstCard() {
        return "APPROVED";
    }

    public static String getStatusSecondCard() {
        return "DECLINED";
    }

    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getPreviousMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getZeroMonth() {
        return "00";
    }

    private static String getThirteenMonth() {
        return "13";
    }

    public static String getValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getValidYearPlusOne() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getPreviousYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getPlusSixYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getHolder() {
        return faker.name().fullName();
    }

    public static String getNameRus() {
        return fakerRu.name().lastName() + " " + fakerRu.name().firstName();
    }

    private static String getNameEn() {
        return faker.name().lastName();
    }


    public static String getCVC() {
        return faker.numerify("###");
    }

    private static String getTwoNumber() {
        return faker.numerify("##");
    }

    private static String getOneNumber() {
        return faker.numerify("#");
    }

    private static String getFakerNumberCard() {
        return faker.business().creditCardNumber();
    }

    private static String getFifteenNumber() {
        return approvedCard.substring(0, 18);
    }

    private static String getEmptyField() {
        return " ";
    }

    private static String getSpecialSymbol() {
        return "@#$%^&*()~-+/*?><|";
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(declinedCard, getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getEmptyCardNumber() {
        return new CardInfo(getEmptyField(), getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getOneNumberCardNumber() {
        return new CardInfo(getOneNumber(), getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getFifteenNumberCardNumber() {
        return new CardInfo(getFifteenNumber(), getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getFakerNumberCardNumber() {
        return new CardInfo(getFakerNumberCard(), getValidMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getEmptyMonth() {
        return new CardInfo(approvedCard, getEmptyField(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getOneNumberMonth() {
        return new CardInfo(approvedCard, getOneNumber(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getPreviousMonthInField() {
        return new CardInfo(approvedCard, getPreviousMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getZeroMonthInField() {
        return new CardInfo(approvedCard, getZeroMonth(), getValidYearPlusOne(), getHolder(), getCVC());
    }

    public static CardInfo getThirteenMonthInField() {
        return new CardInfo(approvedCard, getThirteenMonth(), getValidYear(), getHolder(), getCVC());
    }

    public static CardInfo getEmptyYear() {
        return new CardInfo(approvedCard, getValidMonth(), getEmptyField(), getHolder(), getCVC());
    }

    public static CardInfo getPreviousYearInField() {
        return new CardInfo(approvedCard, getValidMonth(), getPreviousYear(), getHolder(), getCVC());
    }

    public static CardInfo getPlusSixYearInField() {
        return new CardInfo(approvedCard, getValidMonth(), getPlusSixYear(), getHolder(), getCVC());
    }


    public static CardInfo getSpecialSymbolInFieldName() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getSpecialSymbol(), getCVC());
    }

    public static CardInfo getNumberInFieldName() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getTwoNumber(), getCVC());
    }

    public static CardInfo getOnlySurnameInFieldName() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getNameEn(), getCVC());
    }

    public static CardInfo getEmptyCVVInFieldCVV() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getHolder(), getEmptyField());
    }

    public static CardInfo getOneNumberInFieldCVV() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getHolder(), getOneNumber());
    }

    public static CardInfo getOTwoNumberInFieldCVV() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getHolder(), getTwoNumber());
    }

    public  static CardInfo getRusName() {
        return new CardInfo(approvedCard, getValidMonth(), getValidYear(), getNameRus(), getCVC());
    }

}





