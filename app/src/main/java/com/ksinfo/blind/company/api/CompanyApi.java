package com.ksinfo.blind.company.api;

import com.ksinfo.blind.company.vo.CompanySearchVO;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CompanyApi {
	@GET("company/search")
	Call<List<CompanySearchVO>> getCompanySearchResult(@Query("companyName") String companyName);



	@FormUrlEncoded
	@POST("member/loginApp")
	Call<HashMap<String, String>> loginApp(@FieldMap HashMap<String, String> params);
}