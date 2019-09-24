package com.beksultan.qr_pay.model.user.param;

public class AuthParam {

    public String login;
    public String password;

    public AuthParam(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isValidLogin() {
        return login != null && !login.trim().isEmpty();
    }

    public boolean isValidPassword() {
        return password != null && !password.trim().isEmpty();
    }
}
