package com.ksinfo.tomodomo.model.vo.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyMainVO {
    private final long companyId;
    private final String companyName;
    private final int allPoint;
    private final float realAllPoint;

    public long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getAllPoint() {
        return allPoint;
    }

    public float getRealAllPoint() {
        return realAllPoint;
    }

    public CompanyMainVO(@JsonProperty("companyId") long companyId, @JsonProperty("companyName")String companyName,@JsonProperty("allPoint") int allPoint,@JsonProperty("realAllPoint") float realAllPoint) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.allPoint = allPoint;
        this.realAllPoint = realAllPoint;
    }
}
