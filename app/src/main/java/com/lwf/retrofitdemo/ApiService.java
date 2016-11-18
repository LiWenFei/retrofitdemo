package com.lwf.retrofitdemo;

import com.lwf.retrofitdemo.net.BaseJsonService;

/**
 * Created by liwenfei on 2016/11/9.
 *
 */

public class ApiService extends BaseJsonService {
//    Call<MyResponse> getInfo(@Query("ip") String ip, @Query("name") String name);
    public Api createRestApi() {
        return baseRetrofit().create(Api.class);
    }

    @Override
    protected String getBaseUrl() {
        return "http://10.0.1.246:8888/cabzoo/";
    }
}
