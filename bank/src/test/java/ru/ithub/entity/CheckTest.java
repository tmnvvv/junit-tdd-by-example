package ru.ithub.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.currency.Currency;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckTest {
    private Check existing;
    private UUID testUUID;

    @BeforeEach
    void setUp() {
        testUUID = UUID.randomUUID();
        existing = Check.newBuilder()
                        .setId(testUUID)
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
                .setId(testUUID)
                .setAmount(500)
                .setCurrency(Currency.USD)
                .build()
        );
    }

    @Test
    void plusTest() {
        assertEquals(existing.plus(200), Check.newBuilder()
                .setId(testUUID)
                .setAmount(300)
                .setCurrency(Currency.USD)
                .build()
        );
    }

    @Test
    void minusTest() {
        assertEquals(existing.minus(40), Check.newBuilder()
                .setId(testUUID)
                .setAmount(60)
                .setCurrency(Currency.USD)
                .build()
        );
    }
}