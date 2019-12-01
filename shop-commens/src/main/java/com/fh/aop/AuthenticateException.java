package com.fh.aop;

import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;

public class AuthenticateException extends  RuntimeException {

    private Integer code;

    public AuthenticateException(ServerEnum serverEnum) {
        super(serverEnum.getMessage());
        this.code=serverEnum.getCode();
    }
    public AuthenticateException(ResponseServer responseServer) {
        super(responseServer.getMessage());
        this.code=responseServer.getCode();
    }

    public Integer getCode() {
        return code;
    }


}
