package com.example.eventlistenerexample.modules.account;

import com.example.eventlistenerexample.infra.mail.EmailMessage;
import com.example.eventlistenerexample.infra.mail.MessageService;
import com.example.eventlistenerexample.infra.mail.PushMessage;
import com.example.eventlistenerexample.modules.listener.CurrentAccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MessageService messageService;
    private final ApplicationEventPublisher applicationEventPublisher;


    /**
     * 회원가입
     *
     * @param signInForm
     * @return AccountDTO
     */
    @Transactional
    public AccountDTO signIn(SignInForm signInForm) {
        log.info("start signIn process : {}", signInForm.getEmail());

        applicationEventPublisher.publishEvent(new CurrentAccountEvent(signInForm.getEmail(), signInForm.getPhoneNumber()));

        // 저장
        Account account = Account.from(signInForm);
        accountRepository.save(account);

//        applicationEventPublisher.publishEvent(new AccountEvent(this, signInForm.getEmail(), signInForm.getPhoneNumber()));

        return AccountDTO.from(account);
    }

    /**
     * 이메일 인증코드 전송
     *
     * @param email 인증메일
     * @return authCode 인증코드
     */
    private String sendSignUpConfirmEmail(String email) {
        String authCode = RandomStringUtils.randomAlphabetic(12);
        EmailMessage emailMessage = EmailMessage.builder().to(email).subject("회원가입 인증코드 전송").message(authCode).build();
        messageService.sendEmail(emailMessage);

        return authCode;
    }

    /**
     * 핸드폰 알림 전송
     *
     * @param phoneNumber 핸드폰 번호
     */
    private void sendSignUpConfirmMessage(String phoneNumber) {
        PushMessage pushMessage = PushMessage.builder().to(phoneNumber).meesage("회원가입 인증코드 전송했습니다. 메일에서 확인바랍니다.").build();
        messageService.pushMessage(pushMessage);
    }
}
