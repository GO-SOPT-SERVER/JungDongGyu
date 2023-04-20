package org.example.util;

public class Request {
    public static final String REQUEST_ROLE = "해당하는 역할을 선택해주세요. (은행원 : 1 / 고객 : 2 / 종료 : 0)";
    public static final String REQUEST_NAME = "이름을 입력해주세요.";
    public static final String REQUEST_PN = "주민번호를 입력해주세요. (\"-\"를 제외한 13자리)";
    public static final String REQUEST_PASSWORD = "비밀번호를 입력해주세요. (숫자 4자리)";
    public static final String REQUEST_ACCOUNT = "계좌번호를 입력해주세요.";
    public static final String REQUEST_TARGET_ACCOUNT = "송금하실 계좌번호를 입력해주세요.";
    public static String REQUEST_AMOUNT(String type) {return type + " 하실 금액을 입력해주세요.";}

}
