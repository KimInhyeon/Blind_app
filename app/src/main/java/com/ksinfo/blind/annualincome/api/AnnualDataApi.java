package com.ksinfo.blind.annualincome.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnnualDataApi {
    @GET("company/annualIncome/saveAnnualData")
    Call<Void> saveAnnualData( @Query("annualIncome") Integer annualIncome,
                               @Query("selectJob") String selectJob,
                               @Query("selectWorkPeriod") Integer selectWorkPeriod,
                               @Query("selectWorkType") Integer selectWorkType,
                               @Query("userId") Long userId);
}
