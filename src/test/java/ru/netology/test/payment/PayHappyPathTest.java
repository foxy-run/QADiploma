package ru.netology.test.payment;


import com.codeborne.selenide.Configuration;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.Data.*;
import static ru.netology.data.SQL.*;

public class PayHappyPathTest {
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
        mainPage.payWithCard();
    }

    @Test
    public void shouldSuccessPayIfValidApprovedCards() {
        val cardNumber = getValidCardNumberApproved();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.successNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getPaymentStatus(paymentId);
        val expectedAmount = "4500000";
        val actualAmount = getAmountPayment(paymentId);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAmount, actualAmount);
    }


    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardNumber = getValidCardNumberDeclined();
        paymentPage.fillCardData(cardNumber, numberOfMonth, year, cardholder, cvv);
        paymentPage.failureNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getPaymentStatus(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }
}
