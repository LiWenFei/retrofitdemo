package com.lwf.retrofitdemo.net;

import com.google.gson.Gson;

import retrofit2.Converter;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liwenfei on 2016/11/18.
 */

public class BaseStringService extends BaseService {

    private static final String TAG = BaseStringService.class.getSimpleName();

    @Override
    protected Converter.Factory getConverterFactory(Gson gson) {
        return ScalarsConverterFactory.create();
    }
}
