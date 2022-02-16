package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.company.CompanySearchVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	@FormUrlEncoded
	@POST("company/review/write")//계정정보 추가 필요
	Call<HashMap<String,String>> writeCompanyReviewApi(@FieldMap Map<String,Object> params);


}