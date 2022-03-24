package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.member.MemberVO;
import com.ksinfo.tomodomo.model.vo.mypage.ModifyProfileVO;
import com.ksinfo.tomodomo.model.vo.mypage.MyCompanyReviewVO;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MemberItf {
	@POST("member/registMemberApp")
	Call<HashMap<String, String>> registMemberApp(@QueryMap HashMap<String, String> params);

	@FormUrlEncoded
	@POST("member/loginApp")
	Call<HashMap<String, String>> loginApp(@FieldMap HashMap<String, String> params);

	@GET("member/modifyProfileApp")
	Call<MemberVO> modifyProfileApp(@Query ("userId") String userId);

	@FormUrlEncoded
	@POST("member/checkUpdateProfileApp")
	Call<HashMap<String, String>> checkUpdateProfileApp(@FieldMap HashMap<String, String> params);

	@FormUrlEncoded
	@POST("member/checkNickNameApp")
	Call<HashMap<String, String>> checkNickNameApp(@FieldMap HashMap<String, String> params);

	@FormUrlEncoded
	@POST("member/checkChangePasswordApp")
	Call<HashMap<String, String>> checkChangePasswordApp(@FieldMap HashMap<String, String> params);

	@FormUrlEncoded
	@POST("member/checkSamePasswordApp")
	Call<HashMap<String, String>> checkSamePasswordApp(@FieldMap HashMap<String, String> params);

}