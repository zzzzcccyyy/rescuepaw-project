package com.rescuepaw.exception;

// 自定义异常类，继承自 RuntimeException
public class InvalidParameterException extends RuntimeException {

    // 构造函数，接收错误信息作为参数
    public InvalidParameterException(String message) {
        // 调用父类 RuntimeException 的构造函数，传入错误信息
        super(message);
    }
}