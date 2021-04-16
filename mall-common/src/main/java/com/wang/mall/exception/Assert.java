package com.wang.mall.exception;

/*断言处理类，用于抛出各种api异常*/
public class Assert {
    public static void fail(String message) {
        throw new ApiException(message);
    }
}
