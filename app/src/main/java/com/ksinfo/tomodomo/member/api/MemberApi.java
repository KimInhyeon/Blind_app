package com.ksinfo.tomodomo.member.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface MemberApi {
	@POST("member/registMemberApp")
	Call<HashMap<String, String>> registMemberApp(@QueryMap HashMap<String, String> params);

	@FormUrlEncoded
	@POST("member/loginApp")
	Call<HashMap<String, String>> loginApp(@FieldMap HashMap<String, String> params);
}