package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompanyJobGroupApi {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    @GET("company/annualIncome/annualIncomeCalculator999")
    Call<Void> requestSample999(@Query("string") String string);
}
