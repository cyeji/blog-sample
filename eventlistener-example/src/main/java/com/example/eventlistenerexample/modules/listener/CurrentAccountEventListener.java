package com.example.eventlistenerexample.modules.listener;

import com.example.eventlistenerexample.infra.mail.EmailMessage;
import com.example.eventlistenerexample.infra.mail.MessageService;
import com.example.eventlistenerexample.infra.mail.PushMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentAccountEventListener {

    private final MessageService messageService;

    /**
     * 이메일 인증코드 전송
     *
     * @param event 인증메일
     * @return authCode 인증코드
     */
    @EventListener
    public void sendSignUpConfirmEmail(CurrentAccountEvent event) {
        log.info("send SignUp Confirm Email");
        String authCode = RandomStringUtils.randomAlphabetic(12);
        EmailMessage emailMessage = EmailMessage.builder().to(event.email()).subject("회원가입 인증코드 전송").message(authCode).build();
        messageService.sendEmail(emailMessage);
    }

    /**
     * 핸드폰 알림 전송
     *
     * @param event event
     */
    @EventListener
    public void sendSignUpConfirmMessage(CurrentAccountEvent event) {
        log.info("send SignUp Confirm Message");
        PushMessage pushMessage = PushMessage.builder().to(event.phoneNumber()).meesage("회원가입 인증코드 전송했습니다. 메일에서 확인바랍니다.").build();
        messageService.pushMessage(pushMessage);
    }

}
