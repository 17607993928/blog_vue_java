package com.xjs.common.lang;


public class AjaxMessage<T> {

    private boolean status;
    private T data;
    private String message;
    private String code;

    public AjaxMessage() {
    }

    public AjaxMessage(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public AjaxMessage(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static AjaxMessage success() {
        return success("操作成功");
    }

    public static AjaxMessage success(String message) {
        AjaxMessage cr = new AjaxMessage(true, message, "200");
        return cr;
    }

    public static AjaxMessage error() {
        AjaxMessage cr = new AjaxMessage(false, "操作失败", "500");
        return cr;
    }

    public AjaxMessage data(T data) {
        this.data = data;
        return this;
    }

    public static AjaxMessage error(String message, String code) {
        return error(false, code, message);
    }

    public static AjaxMessage error(boolean status, String code, String message) {
        AjaxMessage cr = new AjaxMessage(status, message, code);
        return cr;
    }

    public static AjaxMessage error(String message) {
        return error(false, "0501", message);
    }

    public boolean isStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}
