package com.example.eventlistenerexample.infra.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushMessage {

    private String to;

    private String meesage;
}
