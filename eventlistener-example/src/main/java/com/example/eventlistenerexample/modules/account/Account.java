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

    /** 메세지 전송 확인 용 */
    private boolean sentMessage;


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

    public void updateSentMessage() {
        this.sentMessage = true;
    }

}
