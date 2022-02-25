package com.ksinfo.tomodomo.model.vo.annualincome;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyBusinessTypeDto {
    private String businessTypeCode;
    private String businessTypeName;

   public CompanyBusinessTypeDto(@JsonProperty("businessTypeCode") String businessTypeCode,
                                 @JsonProperty("businessTypeName") String businessTypeName )
   {
       this.businessTypeCode = businessTypeCode;
       this.businessTypeName = businessTypeName;
   }

    public String getBusinessTypeCode() { return businessTypeCode; }
    public String getBusinessTypeName() {
        return businessTypeName;
    }
}
