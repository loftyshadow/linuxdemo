package com.example.linuxdemo.utils;

public class CmdResult {

    int Code;

    String SucMessage;

    String ErrMessage;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getSucMessage() {
        return SucMessage;
    }

    public void setSucMessage(String sucMessage) {
        SucMessage = sucMessage;
    }

    public String getErrMessage() {
        return ErrMessage;
    }

    public void setErrMessage(String errMessage) {
        ErrMessage = errMessage;
    }

    @Override
    public String toString() {
        return "CmdResult{" +
                "Code=" + Code +
                ", SucMessage='" + SucMessage + '\'' +
                ", ErrMessage='" + ErrMessage + '\'' +
                '}';
    }
}
