package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.AnnualIncomeRankVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnnualIncomeInterface {
    @GET("company/annualIncome/getAnnualIncomeFristPage")
    Call<AnnualIncomeRankVO> getAnnualIncomeFristPage(@Query("userId") Long userId);

    @GET("company/annualIncome/getAnnualIncomeUpdateToSelectedSpinner")
    Call<AnnualIncomeRankVO> getAnnualIncomeUpdateToSelectedSpinner(@Query("selectBusinessType") String selectBusinessType,
                                                                    @Query("selectWorkPeriod") String selectWorkPeriod,
                                                                    @Query("userId") Long userId);

}