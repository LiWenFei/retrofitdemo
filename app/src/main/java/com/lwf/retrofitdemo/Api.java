package com.lwf.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liwenfei on 2016/11/9.
 */

public interface Api {
    /**
     *
     * @param ip
     * @param name
     * @return
     */
//    @GET("service/getIpInfo.php")
//    Call<MyResponse> getInfo(@Query("ip") String ip, @Query("name") String name);

    /**
     *
     * @param name
     * @param password
     * @return
     */
    @POST("szoo2/user/login")
    @FormUrlEncoded
    Call<User> getInfo(@Field("name") String name, @Field("password") String password);

    /**
     *
     * @param name
     * @param password
     * @return
     */
    @POST("szoo2/user/login")
    @FormUrlEncoded
    Call<String> getfirstInfo(@Field("name") String name, @Field("password") String password);
}
