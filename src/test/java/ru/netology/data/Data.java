package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class Data {
    @Value
    public static class CardNumber {
        String CardNumber;
    }

    public static CardNumber getValidCardNumberApproved() {
        return new CardNumber("4444444444444441");
    }

    public static CardNumber getValidCardNumberDeclined() {
        return new CardNumber("4444444444444442");
    }

    public static CardNumber getInvalidCardNumberIfEmpty() {
        return new CardNumber("");
    }

    public static CardNumber getInvalidCardNumberIfLess16Sym() {
        return new CardNumber("4444444444444");
    }

    public static CardNumber getInvalidCardNumberIfOutOfBase() {
        return new CardNumber("5469444444444441");
    }


    @Value
    public static class NumberOfMonth {
        String NumberOfMonth;
    }

    public static NumberOfMonth getValidNumberOfMonth() {
        return new NumberOfMonth("09");
    }

    public static NumberOfMonth getInvalidNumberOfMonthIfEmpty() {
        return new NumberOfMonth("");
    }

    public static NumberOfMonth getInvalidNumberOfMonthIfOneSym() {
        return new NumberOfMonth("1");
    }

    public static NumberOfMonth getInvalidNumberOfMonthIfMore12() {
        return new NumberOfMonth("20");
    }

    public static NumberOfMonth getInvalidNumberOfMonthIfZero() {
        return new NumberOfMonth("00");
    }

    @Value
    public static class Year {
        String Year;
    }

    public static Year getValidYear() {
        return new Year("24");
    }

    public static Year getInvalidYearIfEmpty() {
        return new Year("");
    }

    public static Year getInvalidYearIfOneSym() {
        return new Year("1");
    }

    public static Year getInvalidYearIfBeforeCurrentYear() {
        return new Year("19");
    }

    public static Year getInvalidYearIfZero() {
        return new Year("00");
    }

    public static Year getInvalidYearIfInTheFarFuture() {
        return new Year("40");
    }

    @Value
    public static class Cardholder {
        String Cardholder;
    }

    public static Cardholder getValidCardholderName() {
        return new Cardholder("Popov Igor");
    }

    public static Cardholder getInvalidCardholderNameIfEmpty() {
        return new Cardholder("");
    }

    public static Cardholder getInvalidCardholderNameIfOneWord() {
        return new Cardholder("Popov");
    }

    public static Cardholder getInvalidCardholderNameIfThreeWords() {
        return new Cardholder("Popov Igor Petrovich");
    }

    public static Cardholder getInvalidCardholderNameIfRusSym() {
        return new Cardholder("Попов Игорь");
    }

    public static Cardholder getInvalidCardholderNameIfNumeric() {
        return new Cardholder("5432 3232");
    }

    public static Cardholder getInvalidCardholderNameIfWildcard() {
        return new Cardholder("#%№");
    }

    @Value
    public static class Cvv {
        String Cvv;
    }

    public static Cvv getValidCvv() {
        return new Cvv("123");
    }

    public static Cvv getInvalidCvvIfEmpty() {
        return new Cvv("");
    }

    public static Cvv getInvalidCvvIfOneSym() {
        return new Cvv("1");
    }

    public static Cvv getInvalidCvvIfTwoSym() {
        return new Cvv("12");
    }

}

