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
        assertEquals(new Check(10, Currency.USD), checkFactory.dollar(10));
    }

    @Test
    void gettingEuroTest() {
        assertEquals(new Check(10, Currency.EUR), checkFactory.euro(10));
    }

    @Test
    void gettingRubbleTest() {
        assertEquals(new Check(10, Currency.RUB), checkFactory.rubble(10));
    }

    @Test
    void gettingFrancTest() {
        assertEquals(new Check(10, Currency.CHF), checkFactory.franc(10));
    }
}