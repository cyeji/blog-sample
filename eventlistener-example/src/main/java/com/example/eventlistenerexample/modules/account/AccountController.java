package com.example.eventlistenerexample.modules.account;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> signIn(SignInForm signInForm) {
        return ResponseEntity.ok(accountService.signIn(signInForm));
    }

}
