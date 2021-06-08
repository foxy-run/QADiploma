package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.SqlHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;
import static ru.netology.data.SqlHelper.*;

public class CreditPayHappyPathTest extends TestBaseUI {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldSuccessCreditRequestIfValidApprovedCards() throws SQLException {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();
        assertEquals("APPROVED", SqlHelper.getCreditStatus());
        assertNotEquals(SqlHelper.getOrderInformation(), null);

        val expectedStatus = "APPROVED";
        val actualStatus = getCreditStatus();
        assertEquals(expectedStatus, actualStatus);

        val bankIdExpected = getCreditStatus();
        val paymentIdActual = getCreditStatus();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(bankIdExpected, paymentIdActual);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() throws SQLException {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();

        val expectedStatus = "DECLINED";
        val actualStatus = getCreditStatus();
        assertEquals(expectedStatus, actualStatus);
        val bankIdExpected = getCreditStatus();
        val paymentIdActual = getCreditStatus();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertNotEquals(bankIdExpected, paymentIdActual);
    }
}