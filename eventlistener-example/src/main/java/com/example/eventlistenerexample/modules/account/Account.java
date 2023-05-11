package com.example.eventlistenerexample.modules.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    private String email;

    @Column(unique = true)
    private String nickName;

    private String password;

    private String authCode;

    private String phoneNumber;


    public static Account from(SignInForm signInForm) {
        Account account = new Account();
        account.email = signInForm.getEmail();
        account.nickName = signInForm.getNickName();
        account.password = signInForm.getPassword();
        account.phoneNumber = signInForm.getPhoneNumber();
        return account;
    }

    public void updateAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
