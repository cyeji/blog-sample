package com.example.eventlistenerexample.infra.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsoleMessageService implements MessageService {

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        log.info("sent email: {}", emailMessage.getSubject());
    }

    @Override
    public void pushMessage(PushMessage pushMessage) {
        log.info("send message: {}", pushMessage.getMeesage());
    }

}
