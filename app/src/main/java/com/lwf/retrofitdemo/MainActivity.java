package com.lwf.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lwf.retrofitdemo.net.HttpUtil;
import com.lwf.retrofitdemo.net.MyCallBack;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Api apiService = new ApiService().createRestApi();

        Call<User> call = apiService.getInfo("12345678900", "123456");
        HttpUtil.execute(this, call, new MyCallBack<User>() {
            @Override
            public void onSuccess(Call<User> call, User bean) {
            }
        });
    }
}
