package ru.ithub.util.logging;

import java.util.logging.Logger;

public abstract class AutoLoggedComponent {
    public Logger getLogger() {
        return Logger.getGlobal();
    }
}
