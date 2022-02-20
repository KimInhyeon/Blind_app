package com.ksinfo.tomodomo.model.vo.annualincome;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyBusinessTypeVO {
    private String businessTypeCode;
    private String businessTypeName;

   public CompanyBusinessTypeVO(@JsonProperty("businessTypeCode") String businessTypeCode,
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
