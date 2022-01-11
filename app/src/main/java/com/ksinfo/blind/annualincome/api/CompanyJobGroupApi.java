package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CompanyJobGroupApi {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    @POST("company/annualIncome/annualIncomeCalculator2")
    Call<List<CompanyJobGroupVO>> requestSample();
}
