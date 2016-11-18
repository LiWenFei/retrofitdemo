package com.lwf.retrofitdemo.net;

import com.google.gson.Gson;

import retrofit2.Converter;

/**
 * Created by liwenfei on 2016/11/9.
 */

public abstract class BaseJsonService extends BaseService {

    private static final String TAG = BaseJsonService.class.getSimpleName();

    @Override
    protected Converter.Factory getConverterFactory(Gson gson) {
        return ResponseJsonConverterFactory.create(gson);
    }
}
