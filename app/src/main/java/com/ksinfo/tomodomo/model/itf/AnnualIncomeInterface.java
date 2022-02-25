package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.AnnualIncomeRankDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnnualIncomeInterface {

    @GET("company/annualIncome/getAnnualIncomeAndRank")
    Call<AnnualIncomeRankDto> getAnnualIncomeAndRank(@Query("selectBusinessTypeCode") String selectBusinessTypeCode,
                                                     @Query("selectJobGroupCode") String selectJobGroupCode,
                                                     //@Query("selectWorkPeriod") String selectWorkPeriod,  //勤務期間Spinner機能を追加する時に使う予定。
                                                     @Query("userId") Long userId);

}