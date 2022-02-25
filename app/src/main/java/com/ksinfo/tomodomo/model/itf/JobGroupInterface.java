package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyJobGroupDto;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobGroupInterface {
    //메모 연봉등록페이지에서 모든직군들을 리턴해주는 메소드.
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupDto>> getJobGroupListAll();

    //메모 유저의 직군코드를 수신.
    //메모 RankPage 최초출력(oncreate()매소드)시 '유저가 속한 업계의 직군의 최대/최소/평균 연봉'을 갖고 오기위한 파라미터 값.
    @GET("company/annualIncome/getUserJobGroupCode")
    Call<HashMap<String, String> > getUserJobGroupCode(@Query("userId") Long userId);

    //[메모] 연봉정보가 등록되어 있는 직군들의 이름만 갖고 온다.
    //[메모] 전체가 아닌 연봉정보가 있는 직군만 불러오는 이유는 연봉정보가 없는 직군을 선택하면 Null값 에러가 발생하게 된다.
    //[메모] AIData : 연봉(AnnualIncome)데이터.(인공지능의 AI가 아님.)
    @GET("company/annualIncome/getJobGroupListExistAIData")
    Call<List<CompanyJobGroupDto>> getJobGroupListExistAIData( );
}
