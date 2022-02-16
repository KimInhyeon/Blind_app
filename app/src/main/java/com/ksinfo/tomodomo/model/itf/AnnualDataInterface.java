package com.ksinfo.tomodomo.model.itf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnnualDataInterface {
    @GET("company/annualIncome/saveAnnualData")
    Call<Void> saveAnnualData(@Query("annualIncome") Integer annualIncome,
                              @Query("selectJob") String selectJob,
                              @Query("selectWorkPeriod") Integer selectWorkPeriod,
                              @Query("selectWorkType") Integer selectWorkType,
                              @Query("userId") Long userId);
}