package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CompanyJobGroupApi {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    //    @POST("company/annualIncome/annualIncomeCalculator2")
    //    Call<List<CompanyJobGroupVO>> requestSample();`

    //@FormUrlEncoded
    //@POST("company/annualIncome/annualIncomeCalculator2")
    //Call<List<CompanyJobGroupVO>> requestSample();

    @GET("company/annualIncome/annualIncomeCalculator999")
    Call<Void> requestSample999(@Query("string") String string);
    //Call<String> requestSample999();
}
