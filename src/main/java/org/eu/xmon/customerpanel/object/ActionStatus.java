package org.eu.xmon.customerpanel.object;

public enum ActionStatus {
    LOGGED_IN("Udane logowanie"),
    FAILED_LOGIN_ATTEMPT("Nieudana próba logowania"),
    REGISTRATION_COMPLETED("Aktywacja konta"),
    REGISTRATION_STARTED("Rozpoczęcie rejestracji");

    private String message;

    ActionStatus(String message) {
    }
}
