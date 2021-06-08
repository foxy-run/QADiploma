package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.SqlHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
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
    public void shouldSuccessPayIfValidApprovedCards() throws SQLException {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();

        assertEquals( "APPROVED", SqlHelper.getPaymentStatus());
        val transactionIdExpected = getPaymentStatus();
        val paymentIdActual = getPaymentStatus();
        assertNotNull(transactionIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(transactionIdExpected, paymentIdActual);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() throws SQLException {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();

        assertNotEquals("APPROVED",SqlHelper.getPaymentStatus());

        val transactionIdExpected = getPaymentStatus();
        val paymentIdActual = getPaymentStatus();
        assertNotNull(transactionIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(transactionIdExpected, paymentIdActual);
    }


}