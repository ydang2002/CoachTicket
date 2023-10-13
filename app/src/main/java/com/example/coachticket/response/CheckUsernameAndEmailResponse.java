package com.example.coachticket.response;

public class CheckUsernameAndEmailResponse {
    private boolean usernameExists;
    private boolean emailExists;

    public boolean isUsernameExists() {
        return usernameExists;
    }

    public boolean isEmailExists() {
        return emailExists;
    }
}
