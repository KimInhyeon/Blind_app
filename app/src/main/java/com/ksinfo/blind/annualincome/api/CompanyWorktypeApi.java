package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyWorkTypeVO;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface CompanyWorktypeApi {
    @GET("company/annualIncome/annualIncomeCalculator2")
    Call<List<CompanyWorkTypeVO>> getWorkTypeAll();
}
