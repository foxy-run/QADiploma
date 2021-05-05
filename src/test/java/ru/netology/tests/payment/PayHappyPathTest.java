package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;
import static ru.netology.data.SqlHelper.*;

public class PayHappyPathTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldSuccessPayIfValidApprovedCards() {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();

        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus, actualStatus);

        val expectedAmount = "4500000";
        val actualAmount = getAmountPayment();
        assertEquals(expectedAmount, actualAmount);

        val transactionIdExpected = getTransactionId();
        val paymentIdActual = getPaymentIdForCardPay();
        assertNotNull(transactionIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(transactionIdExpected, paymentIdActual);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();

        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus, actualStatus);

        val transactionIdExpected = getTransactionId();
        val paymentIdActual = getPaymentIdForCardPay();
        assertNotNull(transactionIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(transactionIdExpected, paymentIdActual);
    }
}