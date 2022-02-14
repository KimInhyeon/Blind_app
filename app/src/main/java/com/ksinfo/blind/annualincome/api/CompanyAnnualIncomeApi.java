package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyAnnualIncomeForAndroidVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompanyAnnualIncomeApi {
    @GET("company/annualIncome/getAnnualIncomeFristPage")
    Call<CompanyAnnualIncomeForAndroidVO> getAnnualIncomeFristPage(@Query("userId") Long userId);

    @GET("company/annualIncome/getAnnualIncomeUpdateToSelectedSpinner")
    Call<CompanyAnnualIncomeForAndroidVO> getAnnualIncomeUpdateToSelectedSpinner( @Query("annualIncome") Integer selectBusinessType,
                                                                                  @Query("selectJob") String selectJobGroup,
                                                                                  @Query("selectWorkPeriod") Integer selectWorkPeriod,
                                                                                  @Query("userId") Long userId);

}
