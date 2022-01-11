package com.ksinfo.blind.annualincome.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanyJobGroupVO {
	private final String jobGroupCode;
	private final String jobGroupName;
//	private final boolean exist;

	public CompanyJobGroupVO(
			@JsonProperty("jobGroupCode") String jobGroupCode,
			@JsonProperty("jobGroupName") String jobGroupName
//			@JsonProperty("exist") boolean exist
	) {
		this.jobGroupCode = jobGroupCode;
		this.jobGroupName = jobGroupName;
//		this.exist = exist;
	}

	public String getJobGroupCode() { return jobGroupCode; }
	public String getJobGroupName() {
		return jobGroupName;
	}
	//public boolean isExist() { return exist; }

	//test code for data received check.
	@Override
	public String toString(){
		return "jobGroupCode{" +
			   "jobGroupCode=" + jobGroupCode +
			   ", jobGroupName='" + jobGroupName + '\'' +
		'}';
	}

}