package com.ksinfo.tomodomo.model.vo.annualincome;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyBusinessTypeVO {
    private String businessTypeName;

   public CompanyBusinessTypeVO(@JsonProperty("businessTypeName") String businessTypeName ){
       this.businessTypeName = businessTypeName;
   }

    public String getBusinessTypeName() {
        return businessTypeName;
    }
}
