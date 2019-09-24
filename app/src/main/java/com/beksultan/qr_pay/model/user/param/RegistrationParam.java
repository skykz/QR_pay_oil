package com.beksultan.qr_pay.model.user.param;

public class RegistrationParam {

    public String login;
    public String password;
    public String name;
    public String surname;
    public String phone;
    public String email;
    public String birthdate;

    public RegistrationParam(String login, String password, String name, String surname, String phone, String email, String birthdate) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
    }

    public boolean isValidLogin() {
        return login != null && !login.trim().isEmpty();
    }

    public boolean isValidPassword() {
        return password != null && !password.trim().isEmpty();
    }

    public boolean isValidName() {
        return name != null && !name.trim().isEmpty();
    }

    public boolean isValidSurname() {
        return surname != null && !surname.trim().isEmpty();
    }

    public boolean isValidPhone() {
        return phone != null && !phone.trim().isEmpty();
    }

    public boolean isValidEmail() {
        return email != null && !email.trim().isEmpty();
    }

    public boolean isValidBirthDate() {
        return birthdate != null && !birthdate.trim().isEmpty();
    }
}
