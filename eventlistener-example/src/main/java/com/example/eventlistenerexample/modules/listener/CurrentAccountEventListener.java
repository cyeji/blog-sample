package com.example.eventlistenerexample.modules.listener;

import com.example.eventlistenerexample.infra.mail.EmailMessage;
import com.example.eventlistenerexample.infra.mail.MessageService;
import com.example.eventlistenerexample.infra.mail.PushMessage;
import com.example.eventlistenerexample.modules.account.Account;
import com.example.eventlistenerexample.modules.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentAccountEventListener {

    private final MessageService messageService;
    private final AccountRepository accountRepository;

    /**
     * email로 가입된 계정이 있는지 확인
     *
     * @param event event 객체
     */
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @TransactionalEventListener
    public void checkIsExistedUser(CurrentAccountEvent event) {
        log.info("check is existed User");
        // 등록된 이메일 확인
        accountRepository.findByEmail(event.email()).ifPresent(account -> {throw new RuntimeException("이미 가입된 이메일 주소가 있습니다.");});
    }

    /**
     * 이메일 인증코드 전송
     *
     * @param event 인증메일
     * @return authCode 인증코드
     */
    @TransactionalEventListener
    public void sendSignUpConfirmEmail(CurrentAccountEvent event) {
        log.info("send SignUp Confirm Email");
        String authCode = RandomStringUtils.randomAlphabetic(12);
        EmailMessage emailMessage = EmailMessage.builder().to(event.email()).subject("회원가입 인증코드 전송").message(authCode).build();
        messageService.sendEmail(emailMessage);

        Account account = accountRepository.findByEmail(event.email()).orElseThrow(() -> new NoSuchMessageException("찾을 수 없는 계정입니다."));
        account.updateAuthCode(authCode);
        log.info("account update authCode");
    }

    /**
     * 핸드폰 알림 전송
     *
     * @param event event
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendSignUpConfirmMessage(CurrentAccountEvent event) {
        log.info("send SignUp Confirm Message");
        PushMessage pushMessage = PushMessage.builder().to(event.phoneNumber()).meesage("회원가입 인증코드 전송했습니다. 메일에서 확인바랍니다.").build();
        messageService.pushMessage(pushMessage);

        Account account = accountRepository.findByEmail(event.email()).orElseThrow(() -> new NoSuchMessageException("찾을 수 없는 계정입니다."));
        account.updateSentMessage();
        log.info("account update send message status");
    }

}
