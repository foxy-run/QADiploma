package ru.netology.tests.creditrequest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.data.SQL;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.Data.*;

public class CreditPayYearFieldTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();
    private final Data.CardNumber cardNumber = getValidCardNumberApproved();
    private final Data.NumberOfMonth numberOfMonth = getValidNumberOfMonth();
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
    public void shouldFailurePaymentIfEmptyYear() {
        val year = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.emptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearOneSym() {
        val year = getInvalidYearIfOneSym();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearBeforeCurrentYear() {
        val year = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.expiredDatePassNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearZero() {
        val year = getInvalidYearIfZero();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.invalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearInTheFarFuture() {
        val year = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.invalidExpiredDateNotification();
    }
}
