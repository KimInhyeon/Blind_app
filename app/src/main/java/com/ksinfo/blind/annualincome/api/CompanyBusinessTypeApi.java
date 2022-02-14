package com.ksinfo.blind.annualincome.api;

import com.ksinfo.blind.annualincome.vo.CompanyBusinessTypeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyBusinessTypeApi {
    @GET("company/annualIncome/getBusinessTypeNameList")
    Call<List<CompanyBusinessTypeVO>> getBusinessTypeNameList();
}
