package ru.ithub.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.entity.Check;
import ru.ithub.currency.Currency;
import ru.ithub.factory.impl.CheckFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class CheckFactoryTest {
    private CheckFactory checkFactory;

    @BeforeEach
    void setUp() {
        checkFactory = new CheckFactoryImpl();
    }

    @Test
    void gettingDollarTest() {
        assertEquals(
                Check.newBuilder()
                    .setAmount(10)
                    .setCurrency(Currency.USD)
                    .build(),
                checkFactory.dollar(10)
        );
    }

    @Test
    void gettingEuroTest() {
        assertEquals(
                Check.newBuilder()
                        .setAmount(10)
                        .setCurrency(Currency.EUR)
                        .build(),
                checkFactory.euro(10)
        );
    }

    @Test
    void gettingRubbleTest() {
        assertEquals(
                Check.newBuilder()
                        .setAmount(10)
                        .setCurrency(Currency.RUB)
                        .build(),
                checkFactory.rubble(10)
        );
    }

    @Test
    void gettingFrancTest() {
        assertEquals(
                Check.newBuilder()
                        .setAmount(10)
                        .setCurrency(Currency.CHF)
                        .build(),
                checkFactory.franc(10)
        );
    }
}