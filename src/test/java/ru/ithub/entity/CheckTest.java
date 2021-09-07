package ru.ithub.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.currency.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    private Check existing;

    @BeforeEach
    void setUp() {
        existing = Check.newBuilder()
                        .setAmount(100)
                        .setCurrency(Currency.USD)
                        .build();
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
        assertEquals(existing.times(5), Check.newBuilder()
                .setAmount(500)
                .setCurrency(Currency.USD)
                .build()
        );
    }

    @Test
    void plusTest() {
        assertEquals(existing.plus(200), Check.newBuilder()
                .setAmount(300)
                .setCurrency(Currency.USD)
                .build()
        );
    }

    @Test
    void minusTest() {
        assertEquals(existing.minus(40), Check.newBuilder()
                .setAmount(60)
                .setCurrency(Currency.USD)
                .build()
        );
    }
}