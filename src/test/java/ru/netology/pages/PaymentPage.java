package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement numberOfMonthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final ElementsCollection fieldSet = $$(".input__control");
    private final SelenideElement cardholderField = fieldSet.get(3);
    private final SelenideElement cvvField = $("input[placeholder='999']");

    private final SelenideElement improperFormat =  $(byText("Неверный формат"));
    private final SelenideElement emptyField =  $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidExpiredDate =  $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expiredDatePass =  $(byText("Истёк срок действия карты"));
    private final SelenideElement successNote =  $(byText("Операция одобрена Банком."));
    private final SelenideElement failureNote =  $(byText("Ошибка! Банк отказал в проведении операции."));

    private final SelenideElement continueButton =  $$("button").find(exactText("Продолжить"));

    public void fillCardData(Data.CardData cardData) {
        cardNumberField.setValue(cardData.getNumber());
        numberOfMonthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        cardholderField.setValue(cardData.getHolder());
        cvvField.setValue(cardData.getCvv());
        continueButton.click();
    }

    public void shouldImproperFormatNotification() {
        improperFormat.shouldBe(Condition.visible);
    }

    public void shouldEmptyFieldNotification() {
        emptyField.shouldBe(Condition.visible);
    }

    public void shouldInvalidExpiredDateNotification() {
        invalidExpiredDate.shouldBe(Condition.visible);
    }

    public void shouldExpiredDatePassNotification() {
        expiredDatePass.shouldBe(Condition.visible);
    }

    public void shouldSuccessNotification() {
        successNote.waitUntil(Condition.visible, 15000);
    }

    public void shouldFailureNotification() {
        failureNote.waitUntil(Condition.visible, 15000);
    }

}