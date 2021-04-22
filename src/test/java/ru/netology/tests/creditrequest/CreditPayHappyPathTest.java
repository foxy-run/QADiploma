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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.Data.*;
import static ru.netology.data.Data.getValidCardNumberDeclined;
import static ru.netology.data.SQL.*;

public class CreditPayHappyPathTest {
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
    public void shouldSuccessCreditRequestIfValidApprovedCards() {
        val cardNumber = getValidCardNumberApproved();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.successNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getPaymentStatusForCreditRequest(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardNumber = getValidCardNumberDeclined();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.failureNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getPaymentStatusForCreditRequest(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

}
