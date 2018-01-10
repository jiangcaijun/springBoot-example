package com.backstage.base.response;

import java.util.Date;

public class XPFExceptionResponse extends XPFBaseResponse {

    public XPFExceptionResponse(String errorCode, String errorHuman, Object reason) {
        super(false);
        super.put("errorCode", errorCode);
        super.put("errorHuman", errorHuman);
        super.put("time", new Date());
        if(reason != null) {
            super.put("reason", reason);
        }
    }
}
