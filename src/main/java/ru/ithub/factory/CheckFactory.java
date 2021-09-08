package ru.ithub.factory;

import ru.ithub.entity.Check;
import ru.ithub.util.logging.Parameter;

public interface CheckFactory {
    Check dollar(@Parameter(name = "amount of money") double amount);
    Check euro(@Parameter(name = "amount of money") double amount);
    Check rubble(@Parameter(name = "amount of money") double amount);
    Check franc(@Parameter(name = "amount of money") double amount);
}
