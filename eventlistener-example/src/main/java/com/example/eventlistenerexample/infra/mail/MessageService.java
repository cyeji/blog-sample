package com.example.eventlistenerexample.infra.mail;

public interface MessageService {

    void sendEmail(EmailMessage emailMessage);

    void pushMessage(PushMessage pushMessage);
}
