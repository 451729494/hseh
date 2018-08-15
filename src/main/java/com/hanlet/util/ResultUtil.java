package com.hanlet.util;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
public class ResultUtil {
    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<Object>();
        result.setStatus(200);
        result.setDesc("请求成功");
        result.setData(object);
        return result;
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setStatus(code);
        result.setDesc(msg);
        return result;
    }
}
