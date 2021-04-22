package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.API.CreditRequestPage;
import static ru.netology.data.API.PaymentPage;
import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;

public class APITest {
    @Test
    void shouldGetStatusValidApprovedCardPayment() {
        val validApprovedCard = getApprovedCard();
        val status = PaymentPage(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardPayment() {
        val validDeclinedCard = getDeclinedCard();
        val status = PaymentPage(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void shouldGetStatusValidApprovedCardCreditRequest() {
        val validApprovedCard = getApprovedCard();
        val status = CreditRequestPage(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardCreditRequest() {
        val validDeclinedCard = getDeclinedCard();
        val status = CreditRequestPage(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
