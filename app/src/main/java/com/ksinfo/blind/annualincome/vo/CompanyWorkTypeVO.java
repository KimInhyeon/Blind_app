package com.ksinfo.blind.annualincome.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanyWorkTypeVO {
    private final String workTypeName;

    public CompanyWorkTypeVO(
        @JsonProperty("workTypeName") String workTypeName)
    {
        this.workTypeName = workTypeName;
    }

    public String getWorkTypeName(){
        return workTypeName;
    }

}
