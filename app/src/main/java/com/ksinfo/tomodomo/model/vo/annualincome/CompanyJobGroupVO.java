package com.ksinfo.tomodomo.model.vo.annualincome;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanyJobGroupVO {
	private final String jobGroupCode;
	private final String jobGroupName;

	public CompanyJobGroupVO(
			@JsonProperty("jobGroupCode") String jobGroupCode,
			@JsonProperty("jobGroupName") String jobGroupName
	) {
		this.jobGroupCode = jobGroupCode;
		this.jobGroupName = jobGroupName;
	}
	public String getJobGroupCode() { return jobGroupCode; }
	public String getJobGroupName() { return jobGroupName; }

	//test code for data received check.
	@Override
	public String toString(){
		return "jobGroupCode{" +
			   "jobGroupCode=" + jobGroupCode +
			   ", jobGroupName='" + jobGroupName + '\'' +
		'}';
	}

}