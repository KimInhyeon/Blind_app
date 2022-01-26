package com.ksinfo.blind.company.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanySearchVO {
	private final long companyId;
	private final String companyName;

	public CompanySearchVO(@JsonProperty("companyId") long companyId,@JsonProperty("companyName") String companyName) {
		this.companyId = companyId;
		this.companyName = companyName;
	}





	public long getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}
}