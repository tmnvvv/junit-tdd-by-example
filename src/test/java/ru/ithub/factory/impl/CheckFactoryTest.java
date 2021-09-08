package ru.ithub.factory.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.currency.Currency;
import ru.ithub.entity.Check;
import ru.ithub.factory.CheckFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * При тестировании фабрики создания мы не проверяем ID сущности, так как мы её создаём с нуля и он генерируется случайно
 *
 * @see Check
 */
class CheckFactoryTest {
    private CheckFactory checkFactory;

    @BeforeEach
    void setUp() {
        checkFactory = new CheckFactoryImpl();
    }

    @Test
    void gettingDollarTest() {
        Check expected = Check.newBuilder()
                .setAmount(10)
                .setCurrency(Currency.USD)
                .build();

        Check actual = checkFactory.dollar(10);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAmount(), actual.getAmount());
    }

    @Test
    void gettingEuroTest() {
        Check expected = Check.newBuilder()
                .setAmount(10)
                .setCurrency(Currency.EUR)
                .build();

        Check actual =  checkFactory.euro(10);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAmount(), actual.getAmount());
    }

    @Test
    void gettingRubbleTest() {
        Check expected = Check.newBuilder()
                .setAmount(10)
                .setCurrency(Currency.RUB)
                .build();

        Check actual =  checkFactory.rubble(10);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAmount(), actual.getAmount());
    }

    @Test
    void gettingFrancTest() {
        Check expected = Check.newBuilder()
                .setAmount(10)
                .setCurrency(Currency.CHF)
                .build();

        Check actual =  checkFactory.franc(10);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAmount(), actual.getAmount());
    }
}