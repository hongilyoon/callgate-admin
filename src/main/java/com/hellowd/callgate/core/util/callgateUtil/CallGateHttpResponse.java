package com.hellowd.callgate.core.util.callgateUtil;

import lombok.Data;

@Data
public class CallGateHttpResponse {
    private int code;
    private String auth_key;
    private String svc_code;
    private int status;
    private String error;
    private String message;
    private String prologue_url;
    private String menu_url;
    private String epilogue_url;
    private String use_yn;
}
