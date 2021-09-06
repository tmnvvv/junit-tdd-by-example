package ru.ithub.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.currency.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    private Check existing;

    @BeforeEach
    void setUp() {
        existing = new Check(100, Currency.USD);
    }

    @Test
    void getAmountTest() {
        assertEquals(existing.getAmount(), 100);
    }

    @Test
    void getCurrencyTest() {
        assertEquals(existing.getCurrency(), Currency.USD);
    }

    @Test
    void multiplyTst() {
        assertEquals(existing.times(5), new Check(500, Currency.USD));
    }

    @Test
    void plusTest() {
        assertEquals(existing.plus(200), new Check(300, Currency.USD));
    }

    @Test
    void minusTest() {
        assertEquals(existing.minus(40), new Check(60, Currency.USD));
    }
}