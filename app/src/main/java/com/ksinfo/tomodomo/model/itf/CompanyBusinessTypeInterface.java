package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyBusinessTypeDto;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompanyBusinessTypeInterface {
    @GET("company/annualIncome/getUserBusinessTypeCode")
    Call<HashMap<String, String>> getUserBusinessTypeCode(@Query("userId") Long userId);

    //[메모] 연봉정보가 등록되어 있는 업계들의 이름만 갖고 온다.
    //[메모] 전체가 아닌 연봉정보가 있는 직군만 불러오는 이유는 연봉정보가 없는 직군을 선택하면 Null값 에러가 발생하게 된다.
    //[메모] AIData : 연봉(AnnualIncome)데이터.(인공지능의 AI가 아님.)
    @GET("company/annualIncome/getBusinessTypeListExistAIData")
    Call<List<CompanyBusinessTypeDto>> getBusinessTypeListExistAIData( );

}