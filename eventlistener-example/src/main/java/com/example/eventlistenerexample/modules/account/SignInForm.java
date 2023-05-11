package com.example.eventlistenerexample.modules.account;

import lombok.Data;

@Data
public class SignInForm {

    /** 사용자 이메일 */
    private String email;

    /** 닉네임 */
    private String nickName;

    /** 패스워드 */
    private String password;

    /** 패스워드 확인 */
    private String rePassword;

    /** 전화전호 */
    private String phoneNumber;

}
