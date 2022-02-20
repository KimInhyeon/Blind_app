package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobGroupInterface {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();

    @GET("company/annualIncome/getUserJobGroupCode")
    Call<CompanyJobGroupVO> getUserJobGroupCode(@Query("userId") Long userId);

    //[메모] 연봉정보가 등록되어 있는 직군들의 이름만 갖고 온다.
    //[메모] 전체가 아닌 연봉정보가 있는 직군만 불러오는 이유는 연봉정보가 없는 직군을 선택하면 Null값 에러가 발생하게 된다.
    //[메모] AIData : 연봉(AnnualIncome)데이터.(인공지능의 AI가 아님.)
    //@GET("company/annualIncome/getJobGroupListExistAIData")
    //Call<List<CompanyJobGroupVO>> getJobGroupListExistAIData( @Query("selectWorkPeriod") String selectWorkPeriod );
    @GET("company/annualIncome/getJobGroupListExistAIData")
    Call<List<CompanyJobGroupVO>> getJobGroupListExistAIData( );


    // Android-年俸計算機の職種_post_test
    //@GET("company/annualIncome/annualIncomeCalculator999")
    //Call<Void> requestSample999(@Query("string") String string);
}
