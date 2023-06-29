package com.sirpho.blog.common.utils;

//import org.apache.http.HttpStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
public class R extends HashMap<String, Object> {
    @Serial
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", HttpStatus.OK.value());
        put("msg", "success");
    }

    public R data(Object data) {
        this.put("data", data);
        return this;
    }

    public static R error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
