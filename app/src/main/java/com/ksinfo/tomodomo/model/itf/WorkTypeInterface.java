package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyWorkTypeDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkTypeInterface {
    @GET("company/annualIncome/annualIncomeCalculator2")
    Call<List<CompanyWorkTypeDto>> getWorkTypeAll();
}