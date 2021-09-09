package ru.ithub.converter;

import ru.ithub.entity.Check;
import ru.ithub.currency.Currency;

public interface CheckConverter {
    Check convert(Check check, Currency currency);
}
