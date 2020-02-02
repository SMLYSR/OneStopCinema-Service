package org.joker.oscp.common.exception;

/**
 * 自定义异常类
 * @author JOKER
 */
public class MyException extends RuntimeException{

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MyException(Throwable throwable) {
        super(throwable);
    }

}
