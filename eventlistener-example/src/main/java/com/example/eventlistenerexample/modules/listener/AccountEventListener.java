package com.example.eventlistenerexample.modules.listener;

import com.example.eventlistenerexample.infra.mail.EmailMessage;
import com.example.eventlistenerexample.infra.mail.MessageService;
import com.example.eventlistenerexample.infra.mail.PushMessage;
import com.example.eventlistenerexample.modules.account.Account;
import com.example.eventlistenerexample.modules.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventListener implements ApplicationListener<AccountEvent> {

    private final MessageService messageService;
    private final AccountRepository accountRepository;

    @Override
    public void onApplicationEvent(AccountEvent event) {
        log.info("AccountEventListener run : ");
        sendSignUpConfirmEmail(event.getEmail());

        sendSignUpConfirmMessage(event.getPhoneNumber());

    }

    /**
     * 이메일 인증코드 전송
     *
     * @param email 이메일
     */
    private void sendSignUpConfirmEmail(String email) {
        log.info("send SignUp Confirm Email");
        String authCode = RandomStringUtils.randomAlphabetic(12);
        EmailMessage emailMessage = EmailMessage.builder().to(email).subject("회원가입 인증코드 전송").message(authCode).build();
        messageService.sendEmail(emailMessage);

        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("등록된 이메일이 없습니다."));
        account.updateAuthCode(authCode);
    }

    /**
     * 인증메일 확인 요청 전송
     *
     * @param phoneNumber 전화전호
     */
    private void sendSignUpConfirmMessage(String phoneNumber) {
        log.info("send SignUp Confirm Message");
        PushMessage pushMessage = PushMessage.builder().to(phoneNumber).meesage("회원가입 인증코드 전송했습니다. 메일에서 확인바랍니다.").build();
        messageService.pushMessage(pushMessage);

    }
}
