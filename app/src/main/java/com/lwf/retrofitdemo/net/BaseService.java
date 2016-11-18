package com.lwf.retrofitdemo.net;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by liwenfei on 2016/11/18.
 */

public abstract class BaseService {

    private static final String ENDPOINT = "http://ip.taobao.com";

    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    //  OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new CatchAndLoggingInterceptor())
//            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(Constants.HTTP_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.HTTP_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.HTTP_TIME_OUT, TimeUnit.MILLISECONDS)
            .build();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();//使用 gson coverter，统一日期请求格式
    private Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(getBaseUrl())
            .addConverterFactory(getConverterFactory(gson))
            .build();

    protected abstract Converter.Factory getConverterFactory(Gson gson);

    protected Retrofit baseRetrofit() {
        return retrofit;
    }

    protected String getBaseUrl() {
        return ENDPOINT;
    }

    private static class CatchAndLoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (TextUtils.equals(request.method(), "GET")) {
                request = addGetCommonParams(request);
            } else if (TextUtils.equals(request.method(), "POST")) {
                request = addPostCommonParams(request);
            }
            return chain.proceed(request);
        }

        //get请求 添加公共参数 签名
        private Request addGetCommonParams(Request request) {
            //添加公共参数
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("ts", System.currentTimeMillis() + "")
                    .addQueryParameter("sign_type", "MD5")
                    .addQueryParameter("sn", "scanner")
                    .build();

            //添加签名
            Set<String> nameSet = httpUrl.queryParameterNames();
            ArrayList<String> nameList = new ArrayList<>();
            nameList.addAll(nameSet);
            Collections.sort(nameList);

            Map<String, String> parm = new HashMap<>();
            for (int i = 0; i < nameList.size(); i++) {
                String value = (httpUrl.queryParameterValues(nameList.get(i)) != null &&
                        httpUrl.queryParameterValues(nameList.get(i)).size() > 0 ? httpUrl.queryParameterValues(nameList.get(i)).get(0) : "");
                parm.put(nameList.get(i), value);
            }
            httpUrl = httpUrl.newBuilder()
                    .addQueryParameter("sign", SignUtils.signByMd5(parm, Constants.SIGN_MD5_KEY_STRING))
                    .build();

            request = request.newBuilder().url(httpUrl).build();
            printGet(request);
            return request;
        }

        //enqueue 添加签名和公共参数
        private Request addPostCommonParams(Request request) throws UnsupportedEncodingException {
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();

                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }

                formBody = bodyBuilder
                        .addEncoded("ts", System.currentTimeMillis() + "")
                        .addEncoded("sign_type", "MD5")
                        .addEncoded("sn", "scanner")
                        .build();

                Map<String, String> bodyMap = new HashMap<>();
                List<String> nameList = new ArrayList<>();

                for (int i = 0; i < formBody.size(); i++) {
                    nameList.add(formBody.encodedName(i));
                    bodyMap.put(formBody.encodedName(i), URLDecoder.decode(formBody.encodedValue(i), "UTF-8"));
                }

                Collections.sort(nameList);
                Map<String, String> parm = new HashMap<>();
                for (int i = 0; i < nameList.size(); i++) {
                    String value = URLDecoder.decode(bodyMap.get(nameList.get(i)), "UTF-8");
                    parm.put(nameList.get(i), value);
                }

                formBody = bodyBuilder.
                        addEncoded("sign", SignUtils.signByMd5(parm, Constants.SIGN_MD5_KEY_STRING))
                        .build();

                request = request.newBuilder().post(formBody).build();
                printPost(request, formBody);
            }
            return request;
        }

        private void printPost(Request request, FormBody formBody) {
            Log.i("asd", "【发起请求：】" + request.url().toString());
            if (formBody.size() <= 0){
                Log.i("asd", "【传参：】 为null" );
                return;
            }

            Map<String, String> parm = new HashMap<>();
            for (int i = 0; i < formBody.size(); i++){
                String value = TextUtils.isEmpty(formBody.encodedValue(i)) != true ? formBody.encodedValue(i) : "";
                parm.put(formBody.encodedName(i), value);
            }
            Log.i("asd", "【传参：】" + parm.toString());
        }

        private void printGet(Request request) {
            Log.i("asd", "【发起请求：】" + request.url().toString());
            if (request.url().querySize() <= 0) {
                Log.i("asd", "【传参：】 为null" );
                return;
            }

            Map<String, String> parm = new HashMap<>();
            for (int i = 0; i < request.url().querySize(); i++){
                String value = TextUtils.isEmpty(request.url().queryParameterValue(i)) != true ? request.url().queryParameterValue(i) : "";
                parm.put(request.url().queryParameterName(i), value);
            }
            Log.i("asd", "【传参：】" + parm.toString());
        }
    }
}
