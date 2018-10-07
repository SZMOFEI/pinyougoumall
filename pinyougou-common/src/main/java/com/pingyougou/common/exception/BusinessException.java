package com.pingyougou.common.exception;

/**
 * 业务逻辑异常信息类
 * @author mofei
 * @date 2018-6-03
 */
public class BusinessException extends RuntimeException {

    private int errCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int errCode) {
        this.errCode = errCode;
    }

    public BusinessException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public BusinessException(int errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public BusinessException(int errCode, Throwable cause) {
        super(cause);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
