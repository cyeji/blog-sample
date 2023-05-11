package com.example.eventlistenerexample.modules.account;

import static org.springframework.beans.BeanUtils.copyProperties;

import lombok.Data;

@Data
public class AccountDTO {

    /** 사용자 이메일 */
    private String email;

    /** 닉네임 */
    private String nickName;

    public AccountDTO(Account account) {
        copyProperties(account, this);
    }

    public static AccountDTO from(Account account) {
        return new AccountDTO(account);
    }
}
