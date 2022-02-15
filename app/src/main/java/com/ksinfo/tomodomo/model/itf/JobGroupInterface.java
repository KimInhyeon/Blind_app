package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobGroupInterface {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    @GET("company/annualIncome/annualIncomeCalculator999")
    Call<Void> requestSample999(@Query("string") String string);
}
