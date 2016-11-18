package com.lwf.retrofitdemo.net;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static android.content.ContentValues.TAG;

/**
 * Created by liwenfei on 2016/11/16.
 * (这里做统一错误处理)对于服务器返回的失败 抛出对应异常，并且添加对JSONObject的类型装换支持
 */

public class GsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            ResultResponse resultResponse = parse(response);
            String body = resultResponse.body;
            if (TextUtils.isEmpty(body))
                throw new IOException(response);
            T responseBody = gson.fromJson(body, type);
            if (resultResponse.isSuccess()) {
                return responseBody;
            } else {
                //ErrResponse 将msg解析为异常消息文本
                throw new ApiException(resultResponse.code, resultResponse.msg);
            }
        } catch (ApiException e) {
            throw new IOException(e);
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    private ResultResponse parse(String responseString) throws JSONException {
        if (responseString == null)
            return null;
        Log.i(TAG, "【准备解析：】" + responseString);

        JSONObject object = new JSONObject(responseString);
        String code = object.getString("code");
        String errmsg = object.getString("msg");
        String body = object.getString("body");

        ResultResponse entity = new ResultResponse();
        if (TextUtils.isEmpty(code))
            throw new JSONException("远程服务意外的返回内容：code = 空");
        entity.code = Integer.parseInt(code);
        entity.msg = errmsg == null ? "" : errmsg;
        entity.body = body == null ? "" : body;
        return entity;
    }
}