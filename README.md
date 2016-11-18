# Retrofit-Demo
**在retrofit网络库基础上，封装自己的网络业务工具库**

* `BaseService`配置`BaseUrl`、网络超时、拦截器（例：日志拦截器）以及配置第三方库的Factory（例：Gson，Rxjava），
   拦截器与Factory都可以自定义
* `BaseStringService`继承`BaseService`，自定义配置`BaseUrl`，网络请求继承
  `BaseStringService`，可获取到全部字符串结果
* `BaseJsonService`继承`BaseService`，自定义配置`BaseUrl`，网络请求继承`BaseJsonService`，
   可获取到服务器body中的Json数据，并转化为JavaBean（**注意：不能直接获取字符串结果**）
* `HttpUtil`中同步请求`execute`要在子线程中开启，结果返回为主线程
* `MyTask`异步任务类，自定义线程池

# 引用库以及配置
  在主Module中的build.gradle配置：
  
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    //Retrofit2所需要的包
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    //ConverterFactory的Gson依赖包
    compile 'com.squareup.retrofit2:converter-scalars:2.1.0'
    //ConverterFactory的String依赖包
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.2'

  在AndroidManifest.xml中配置网络权限
  
    <uses-permission android:name="android.permission.INTERNET" />
    
