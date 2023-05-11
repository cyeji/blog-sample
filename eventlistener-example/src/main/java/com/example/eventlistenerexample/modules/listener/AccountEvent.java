package com.example.eventlistenerexample.modules.listener;

import org.springframework.context.ApplicationEvent;

public class AccountEvent extends ApplicationEvent {

    private final String email;
    private final String phoneNumber;

    public AccountEvent(Object source, String email, String phoneNumber) {
        super(source);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
