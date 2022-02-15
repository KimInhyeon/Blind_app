package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.annualincome.CompanyBusinessTypeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyBusinessTypeInterface {
    @GET("company/annualIncome/getBusinessTypeNameList")
    Call<List<CompanyBusinessTypeVO>> getBusinessTypeNameList();
}
