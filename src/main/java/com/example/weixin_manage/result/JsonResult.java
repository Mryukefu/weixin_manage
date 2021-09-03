package com.example.weixin_manage.result;

public class JsonResult<T> {
    protected int code;
    protected String message;
    protected T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static JsonResult successFail(Boolean result) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        if (result){
            jsonResult.setCode(200);
            jsonResult.setMessage("请求成功");
            return jsonResult;
        }
        jsonResult.setCode(110);
        jsonResult.setMessage("业务繁忙，稍后再试");
        return jsonResult;
    }

    public static <T> JsonResult success(T data) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        jsonResult.setCode(200);
        jsonResult.setMessage("请求成功");
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult fail(String message) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        jsonResult.setCode(110);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult fail() {
        JsonResult<Object> jsonResult = new JsonResult<>();
        jsonResult.setCode(500);
        jsonResult.setMessage("请求失败");
        return jsonResult;
    }



}
