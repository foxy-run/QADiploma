package ru.netology.test.creditrequest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.data.SQL;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.Data.*;

public class CreditPayCardNumberFieldTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();
    private final Data.NumberOfMonth numberOfMonth = getValidNumberOfMonth();
    private final Data.Year year = getValidYear();
    private final Data.Cardholder cardholder = getValidCardholderName();
    private final Data.Cvv cvv = getValidCvv();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void cleanDataBases() {
        SQL.dropDataBase();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        mainPage.payWithCredit();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        val cardNumber = getInvalidCardNumberIfEmpty();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.emptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        val cardNumber = getInvalidCardNumberIfLess16Sym();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        val cardNumber = getInvalidCardNumberIfOutOfBase();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.failureNotification();
    }
}
