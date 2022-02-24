package com.ksinfo.tomodomo.model.vo.company;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyReviewAverageVO  implements Parcelable {
    private final float careerPoint;
    private final float workLifeBalancePoint;
    private final float companyCulturePoint;
    private final float payPoint;
    private final float headPoint;

    public CompanyReviewAverageVO(
            @JsonProperty("careerPoint") float careerPoint,@JsonProperty("workLifeBalancePoint") float workLifeBalancePoint,
            @JsonProperty("companyCulturePoint") float companyCulturePoint,@JsonProperty("payPoint") float payPoint,
            @JsonProperty("headPoint") float headPoint
    ) {
        this.careerPoint = careerPoint;
        this.workLifeBalancePoint = workLifeBalancePoint;
        this.companyCulturePoint = companyCulturePoint;
        this.payPoint = payPoint;
        this.headPoint = headPoint;
    }

    private CompanyReviewAverageVO(Parcel in) {
        careerPoint = in.readFloat();
        workLifeBalancePoint = in.readFloat();
        companyCulturePoint = in.readFloat();
        payPoint = in.readFloat();
        headPoint = in.readFloat();
    }

    public static final Creator<CompanyReviewAverageVO> CREATOR = new Creator<CompanyReviewAverageVO>() {
        @Override
        public CompanyReviewAverageVO createFromParcel(Parcel in) {
            return new CompanyReviewAverageVO(in);
        }

        @Override
        public CompanyReviewAverageVO[] newArray(int size) {
            return new CompanyReviewAverageVO[size];
        }
    };

    public float getCareerPoint() {
        return careerPoint;
    }

    public float getWorkLifeBalancePoint() {
        return workLifeBalancePoint;
    }

    public float getCompanyCulturePoint() {
        return companyCulturePoint;
    }

    public float getPayPoint() {
        return payPoint;
    }

    public float getHeadPoint() {
        return headPoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(careerPoint);
        parcel.writeFloat(workLifeBalancePoint);
        parcel.writeFloat(companyCulturePoint);
        parcel.writeFloat(payPoint);
        parcel.writeFloat(headPoint);
    }
}
