package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class Data {

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

    public static CardData getApprovedCard() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor", "123");
    }

    public static CardData getDeclinedCard() {
        return new CardData("4444 4444 4444 4442", "09", "24", "Popov Igor", "123");
    }

    //

    public static CardData getInvalidCardNumberIfEmpty() {
        return new CardData("", "09", "24", "Popov Igor", "123");
    }

    public static CardData getInvalidCardNumberIfLess16Sym() {
        return new CardData("4444 4444 4444 4", "09", "24", "Popov Igor", "123");
    }

    public static CardData getInvalidCardNumberIfOutOfBase() {
        return new CardData("5469 4444 4444 4441", "09", "24", "Popov Igor", "123");
    }

//

    public static CardData getInvalidNumberOfMonthIfEmpty() {
        return new CardData("4444 4444 4444 4441", "", "24", "Popov Igor", "123");
    }

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData("4444 4444 4444 4441", "1", "24", "Popov Igor", "123");
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData("4444 4444 4444 4441", "20", "24", "Popov Igor", "123");
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData("4444 4444 4444 4441", "00", "24", "Popov Igor", "123");
    }

    //

    public static CardData getInvalidYearIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "", "Popov Igor", "123");
    }

    public static CardData getInvalidYearIfOneSym() {
        return new CardData("4444 4444 4444 4441", "09", "1", "Popov Igor", "123");
    }

    public static CardData getInvalidYearIfBeforeCurrentYear() {
        return new CardData("4444 4444 4444 4441", "09", "19", "Popov Igor", "123");
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData("4444 4444 4444 4441", "09", "00", "Popov Igor", "123");
    }

    public static CardData getInvalidYearIfInTheFarFuture() {
        return new CardData("4444 4444 4444 4441", "09", "40", "Popov Igor", "123");
    }

//

    public static CardData getInvalidCardholderNameIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "24", "", "123");
    }

    public static CardData getInvalidCardholderNameIfOneWord() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov", "123");
    }

    public static CardData getInvalidCardholderNameIfThreeWords() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor Petrovich", "123");
    }

    public static CardData getInvalidCardholderNameIfRusSym() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Попов Игорь", "123");
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData("4444 4444 4444 4441", "09", "24", "5432 3232", "123");
    }

    public static CardData getInvalidCardholderNameIfWildcard() {
        return new CardData("4444 4444 4444 4441", "09", "24", "#%№", "123");
    }

//

    public static CardData getInvalidCvvIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor", "");
    }

    public static CardData getInvalidCvvIfOneSym() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor", "1");
    }

    public static CardData getInvalidCvvIfTwoSym() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor", "12");
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData("4444 4444 4444 4441", "09", "24", "Popov Igor", "000");
    }

    public static CardData getInvalidCardDataIfEmptyAllFields() {
        return new CardData("", "", "", "", "");
    }

}