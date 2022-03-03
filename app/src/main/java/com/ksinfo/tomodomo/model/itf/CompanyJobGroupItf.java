package com.ksinfo.tomodomo.model.itf;



import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CompanyJobGroupItf {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    @POST("company/annualIncome/annualIncomeCalculator2")
    Call<List<CompanyJobGroupVO>> requestSample();

}
