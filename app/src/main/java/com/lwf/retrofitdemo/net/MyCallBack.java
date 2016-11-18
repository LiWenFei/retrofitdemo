package com.lwf.retrofitdemo.net;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liwenfei on 2016/11/17.
 */

public abstract class MyCallBack<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            onSuccess(call, response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("asd", "【ErrorCause】" + t.toString());
    }

    public abstract void onSuccess(Call<T> call, T bean);
}
