package com.ksinfo.tomodomo.util;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RetrofitFactory {
	//自分のPCのIPアドレス。
	private static final String BASE_URL = "http://192.168.0.104:8282/blind/";

	public static Retrofit createJsonRetrofit() {
		return new Retrofit.Builder()
							.baseUrl(BASE_URL)
							.addConverterFactory(JacksonConverterFactory.create())
							.build();
	}

	public static Retrofit createScalaRetrofit() {
		return new Retrofit.Builder()
							.baseUrl(BASE_URL)
							.addConverterFactory(ScalarsConverterFactory.create())
							.build();
	}
}