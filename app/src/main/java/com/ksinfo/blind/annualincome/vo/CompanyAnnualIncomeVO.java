package com.ksinfo.blind.annualincome.vo;

public final class CompanyAnnualIncomeVO {
	private final int minAnnualIncome;
	private final int avgAnnualIncome;
	private final int maxAnnualIncome;

	public CompanyAnnualIncomeVO(int minAnnualIncome, int avgAnnualIncome, int maxAnnualIncome) {
		this.minAnnualIncome = minAnnualIncome;
		this.avgAnnualIncome = avgAnnualIncome;
		this.maxAnnualIncome = maxAnnualIncome;
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
}