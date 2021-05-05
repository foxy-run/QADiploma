package ru.netology.tests.api;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.ApiHelper.*;

import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;

public class APITest {
    @Test
    void shouldGetStatusValidApprovedCardPayment() {
        val validApprovedCard = getApprovedCard();
        val status = paymentPageForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardPayment() {
        val validDeclinedCard = getDeclinedCard();
        val status = paymentPageForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void shouldGetStatusValidApprovedCardCreditRequest() {
        val validApprovedCard = getApprovedCard();
        val status = creditRequestPageForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldGetStatusValidDeclinedCardCreditRequest() {
        val validDeclinedCard = getDeclinedCard();
        val status = creditRequestPageForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

}
