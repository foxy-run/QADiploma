package ru.netology.tests.api;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.API.*;

import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;

public class APITest {
    @Test
    void shouldGetStatusValidApprovedCardPayment() {
        val validApprovedCard = getApprovedCard();
        val status = PaymentPageForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardPayment() {
        val validDeclinedCard = getDeclinedCard();
        val status = PaymentPageForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void shouldGetStatusValidApprovedCardCreditRequest() {
        val validApprovedCard = getApprovedCard();
        val status = CreditRequestPageForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardCreditRequest() {
        val validDeclinedCard = getDeclinedCard();
        val status = CreditRequestPageForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
