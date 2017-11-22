package com.clarkez.common.domain;

/**
 * Created by e23962 on 11/07/2017.
 */
public class ServiceResponse {

    private String user;
    private String msg;
    public ServiceResponse(){

    }
    public ServiceResponse(String user, String msg) {
        this.user = user;
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "user='" + user + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
