package com.ksinfo.blind.annualincome.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanyAnnualIncomeForAndroidVO {
	private final int minAnnualIncome;
	private final int avgAnnualIncome;
	private final int maxAnnualIncome;
	private final int userAnnualIncome;
	private final int countOfParticipant;
	private final float userRank;

	public CompanyAnnualIncomeForAndroidVO(
			@JsonProperty("minAnnualIncome") 	int minAnnualIncome,
			@JsonProperty("avgAnnualIncome") 	int avgAnnualIncome,
			@JsonProperty("maxAnnualIncome") 	int maxAnnualIncome,
			@JsonProperty("userAnnualIncome")   int userAnnualIncome,
			@JsonProperty("countOfParticipant") int countOfParticipant,
			@JsonProperty("userRank") 			float userRank)
	{
		this.minAnnualIncome = minAnnualIncome;
		this.avgAnnualIncome = avgAnnualIncome;
		this.maxAnnualIncome = maxAnnualIncome;
		this.userAnnualIncome = userAnnualIncome;
		this.countOfParticipant = countOfParticipant;
		this.userRank = userRank;
	}

	public int getMinAnnualIncome() {
		return minAnnualIncome;
	}
	public int getAvgAnnualIncome() {
		return avgAnnualIncome;
	}
	public int getMaxAnnualIncome() {
		return maxAnnualIncome;
	}
	public int getUserAnnualIncome() {
		return userAnnualIncome;
	}
	public int getCountOfParticipant() { return countOfParticipant; }
	public float getUserRank() { return userRank; }
}