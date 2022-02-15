package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyWorkTypeVO;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface WorkTypeInterface {
    @GET("company/annualIncome/annualIncomeCalculator2")
    Call<List<CompanyWorkTypeVO>> getWorkTypeAll();
}
