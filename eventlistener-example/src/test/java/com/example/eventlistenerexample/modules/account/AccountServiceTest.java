package com.example.eventlistenerexample.modules.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("SignIn 프로세스 확인")
    void signIn() {
        SignInForm signInForm = new SignInForm();
        String mail = "choyeji1591@gmail.com";
        signInForm.setEmail(mail);
        signInForm.setPhoneNumber("010-1234-5678");
        signInForm.setPassword("choyeji");
        signInForm.setRePassword("choyeji");

        AccountDTO accountDTO = accountService.signIn(signInForm);

        assertEquals(mail, accountDTO.getEmail());

    }
}