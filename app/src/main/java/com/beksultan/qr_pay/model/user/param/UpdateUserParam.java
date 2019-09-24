package com.beksultan.qr_pay.model.user.param;

import com.beksultan.qr_pay.model.user.User;

public class UpdateUserParam {

    public String personId;
    public String name;
    public String surname;
    private String password;
    private String email;
    public String phone;

    public UpdateUserParam(String personId, String name, String surname, String password, String email, String phone) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public boolean isValidName(){
        return name != null && !name.trim().isEmpty();
    }
    public boolean isValidSurname(){ return surname != null && !surname.trim().isEmpty(); }
    public boolean isValidPassword(){
        return password != null && !password.trim().isEmpty();
    }
    public boolean isValidEmail(){
        return email != null && !email.trim().isEmpty();
    }
    public boolean isValidPhone(){
        return phone != null && !phone.trim().isEmpty();
    }

    public User toUser(){
        User user = new User();
        user.name = name;
        user.surname = surname;
        user.password = password;
        user.email = email;
        user.phone = phone;
        return  user;
    }

}
