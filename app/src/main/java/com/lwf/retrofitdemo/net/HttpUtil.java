package com.lwf.retrofitdemo.net;

import android.app.Activity;

import com.lwf.retrofitdemo.thread.MyTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liwenfei on 2016/11/17.
 */

public class HttpUtil {
    /**
     * 异步请求
     *
     * @param call
     * @param callback
     * @param <T>
     */
    public static <T> void enqueue(Call<T> call, Callback<T> callback) {
        call.enqueue(callback);
    }

    /**
     * 同步请求
     *
     * @param context
     * @param call
     * @param callBack
     * @param <T>
     */
    public static <T> void execute(final Activity context, final Call<T> call, final MyCallBack<T> callBack) {
        MyTask.runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<T> response = call.execute();
                    if (response.code() == 200 && response.body() != null) {
                        final T bean = response.body();
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(call, bean);
                            }
                        });
                    } else {
                        throw new IOException();
                    }
                } catch (final IOException e) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure(call, e);
                        }
                    });
                }
            }
        }, true);
    }
}
