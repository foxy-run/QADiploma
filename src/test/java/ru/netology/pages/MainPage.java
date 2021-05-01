package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private static final SelenideElement payWithCardButton = $$("button").find(exactText("Купить"));
    private static final SelenideElement payWithCreditButton = $$("button").find(exactText("Купить в кредит"));
    private static final SelenideElement formOfPayment = $("#root > div > h3");

    public void payWithCard() {
        payWithCardButton.click();
        formOfPayment.shouldHave(text("Оплата по карте"));
    }

    public void payWithCredit() {
        payWithCreditButton.click();
        formOfPayment.shouldHave(text("Кредит по данным карты"));
    }
}
