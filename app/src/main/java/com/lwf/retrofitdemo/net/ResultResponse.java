package com.lwf.retrofitdemo.net;

/**
 * Created by liwenfei on 2016/11/16.
 */

public class ResultResponse {

    public int code;
    public String msg;
    public String body;

    public boolean isSuccess() {
        return code == 0;
    }
}
