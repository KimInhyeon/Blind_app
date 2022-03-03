package com.ksinfo.tomodomo.model.vo.mypage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public final class MyCompanyReviewVO {
	private final long companyReviewId;
	private final String companyName;
	private final String recCreateDate;
	private final String completeFlag;

	public MyCompanyReviewVO(
		@JsonProperty("companyReviewId") long companyReviewId,
		@JsonProperty("companyName") String companyName,
		@JsonProperty("recCreateDate") String recCreateDate,
		@JsonProperty("completeFlag") String completeFlag

	) throws JsonProcessingException {
		this.companyReviewId = companyReviewId;
		this.companyName = companyName;
		this.recCreateDate = recCreateDate;
		this.completeFlag = completeFlag;

	}

	public long getCompanyReviewId() {
		return companyReviewId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getRecCreateDate() {
		return recCreateDate;
	}

	public String getCompleteFlag() {
		return completeFlag;
	}
}