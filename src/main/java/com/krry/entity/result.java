package com.krry.entity;

public class result<T> {
    int code;
    String message;
    private  T data;
    private int num;



    public result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public result(int code, String message, T data){
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public result(int code, String message, T data, int num){
        this.data = data;
        this.code = code;
        this.message = message;
        this.num = num;
    }

    public int getCode() {
        return code;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
