package ru.ithub.factory;

import ru.ithub.entity.Check;

public interface CheckFactory {
    Check dollar(double amount);
    Check euro(double amount);
    Check rubble(double amount);
    Check franc(double amount);
}
