package com.ksinfo.tomodomo.model.vo.mypage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public final class MyTaskReportVO {
	private final String type;
	private final String title;
	private final String groupName; // type='レビュー': jobGroupName, type='ポスト': boardTopicName
	private final String userNickname;
	private final String companyName;
	private final String recCreateDate;
	private final String status;

	public MyTaskReportVO(
		@JsonProperty("type") String type,
		@JsonProperty("title") String title,
		@JsonProperty("groupName") String groupName,
		@JsonProperty("userNickname") String userNickname,
		@JsonProperty("companyName") String companyName,
		@JsonProperty("recCreateDate") String recCreateDate,
		@JsonProperty("status") String status

	) throws JsonProcessingException {
		this.type = type;
		this.title = title;
		this.groupName = groupName;
		this.userNickname = userNickname;
		this.companyName = companyName;
		this.recCreateDate = recCreateDate;
		this.status = status;

	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getRecCreateDate() {
		return recCreateDate;
	}

	public String getStatus() {
		return status;
	}
}