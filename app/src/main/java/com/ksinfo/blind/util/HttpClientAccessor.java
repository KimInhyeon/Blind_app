package com.ksinfo.blind.util;

import com.google.gson.Gson;

import java.util.HashMap;

public class HttpClientAccessor extends HttpClient {
    private static final String ip = "192.168.0.6";  //서버의 주소. 시뮬레이터에서는 localhost의 주소로 실시.

    public static HashMap<String, String> accessByPost(String url, HashMap<String, String> params) throws Exception {
        Builder http = new Builder("POST", "http://" + ip + ":8282/blind/" + url);

        // Parameter Setting
        http.addAllParameters(params);

        HttpClient post = http.create();
        post.request();

        int statusCode = post.getHttpStatusCode();

        if (statusCode > 400) {                 //400이상은 에러코드므로 예외처리
            throw new Exception(new Throwable());
        }

        String body = post.getBody();
        Gson gson = new Gson();
        HashMap<String, String> data = gson.fromJson(body, HashMap.class);

        return data;
    }

    //accessByPost : 요청방식이 POST인 경우 활용하는 메소드로 추정
    public static HashMap<String, String> accessByPost(String url) throws Exception {
        Builder http = new Builder
                ("POST", "http://" + ip + ":8282/blind/" + url);

        HttpClient post = http.create();
        post.request();

        int statusCode = post.getHttpStatusCode();

        if (statusCode > 400) {
            throw new Exception(new Throwable());
        }

        String body = post.getBody();
        Gson gson = new Gson();
        HashMap<String, String> data = gson.fromJson(body, HashMap.class);  //자바에서

        return data;
    }

    public static HashMap<String, String> accessByGet(String url, HashMap<String, String> params) throws Exception {
        Builder http = new Builder
                (null,"http://" + ip + ":8282/blind/" + url);

        // Parameter Setting
        http.addAllParameters(params);

        HttpClient get = http.create();
        get.request();

        int statusCode = get.getHttpStatusCode();

        if (statusCode > 400) {
            throw new Exception(new Throwable());
        }

        String body = get.getBody();
        Gson gson = new Gson();
        HashMap<String, String> data = gson.fromJson(body, HashMap.class);

        return data;
    }

    public static HashMap<String, String> accessByGet(String url) throws Exception {
        Builder http = new Builder
                (null,"http://" + ip + ":8282/blind/" + url);

        HttpClient get = http.create();
        get.request();

        int statusCode = get.getHttpStatusCode();

        if (statusCode > 400) {
            throw new Exception(new Throwable());
        }

        String body = get.getBody();
        Gson gson = new Gson();
        HashMap<String, String> data = gson.fromJson(body, HashMap.class);

        return data;
    }
}
