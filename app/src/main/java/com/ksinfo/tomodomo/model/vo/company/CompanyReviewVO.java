package com.ksinfo.tomodomo.model.vo.company;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompanyReviewVO implements Parcelable {
    private final long companyReviewId;
    private final float allPoint;
    private final byte careerPoint;
    private final byte workLifeBalancePoint;
    private final byte companyCulturePoint;
    private final byte payPoint;
    private final byte headPoint;
    private final String simpleComment;
    private final boolean working;
    private final String userNickname;
    private final String jobGroupName;
    private final String recCreateDate;
    private final String advantages;
    private final String disadvantages;
    private final boolean recommended;
    private final int helpfulCount;

    public CompanyReviewVO(
            @JsonProperty("companyReviewId") long companyReviewId, @JsonProperty("allPoint") float allPoint,
            @JsonProperty("careerPoint") byte careerPoint, @JsonProperty("workLifeBalancePoint") byte workLifeBalancePoint,
            @JsonProperty("companyCulturePoint") byte companyCulturePoint, @JsonProperty("payPoint") byte payPoint,
            @JsonProperty("headPoint") byte headPoint, @JsonProperty("simpleComment") String simpleComment,
            @JsonProperty("working") boolean working, @JsonProperty("userNickname") String userNickname,
            @JsonProperty("jobGroupName") String jobGroupName,
            @JsonProperty("recCreateDate") String recCreateDate, @JsonProperty("advantages") String advantages,
            @JsonProperty("disadvantages") String disadvantages, @JsonProperty("recommended") boolean recommended,
            @JsonProperty("helpfulCount") int helpfulCount
    ) {
        this.companyReviewId = companyReviewId;
        this.allPoint = allPoint;
        this.careerPoint = careerPoint;
        this.workLifeBalancePoint = workLifeBalancePoint;
        this.companyCulturePoint = companyCulturePoint;
        this.payPoint = payPoint;
        this.headPoint = headPoint;
        this.simpleComment = simpleComment;
        this.working = working;
        this.userNickname = userNickname;
        this.jobGroupName = jobGroupName;
        this.recCreateDate = recCreateDate;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
        this.recommended = recommended;
        this.helpfulCount = helpfulCount;
    }

    private CompanyReviewVO(Parcel in) {
        companyReviewId = in.readLong();
        allPoint = in.readFloat();
        careerPoint = in.readByte();
        workLifeBalancePoint = in.readByte();
        companyCulturePoint = in.readByte();
        payPoint = in.readByte();
        headPoint = in.readByte();
        simpleComment = in.readString();
        working = in.readByte() != 0;
        userNickname = in.readString();
        jobGroupName = in.readString();
        recCreateDate = in.readString();
        advantages = in.readString();
        disadvantages = in.readString();
        recommended = in.readByte() != 0;
        helpfulCount = in.readInt();
    }

    public static final Creator<CompanyReviewVO> CREATOR = new Creator<CompanyReviewVO>() {
        @Override
        public CompanyReviewVO createFromParcel(Parcel in) {
            return new CompanyReviewVO(in);
        }

        @Override
        public CompanyReviewVO[] newArray(int size) {
            return new CompanyReviewVO[size];
        }
    };

    public long getCompanyReviewId() {
        return companyReviewId;
    }

    public float getAllPoint() {
        return allPoint;
    }

    public byte getCareerPoint() {
        return careerPoint;
    }

    public byte getWorkLifeBalancePoint() {
        return workLifeBalancePoint;
    }

    public byte getCompanyCulturePoint() {
        return companyCulturePoint;
    }

    public byte getPayPoint() {
        return payPoint;
    }

    public byte getHeadPoint() {
        return headPoint;
    }

    public String getSimpleComment() {
        return simpleComment;
    }

    public boolean isWorking() {
        return working;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public String getRecCreateDate() {
        return recCreateDate;
    }

    public String getAdvantages() {
        return advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public int getHelpfulCount() {
        return helpfulCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {//parcl 패키지안에 멤버변수 다씀
        parcel.writeLong(companyReviewId);
        parcel.writeFloat(allPoint);
        parcel.writeByte(careerPoint);
        parcel.writeByte(workLifeBalancePoint);
        parcel.writeByte(companyCulturePoint);
        parcel.writeByte(payPoint);
        parcel.writeByte(headPoint);
        parcel.writeString(simpleComment);
        parcel.writeByte((byte) (working ? 1 : 0));
        parcel.writeString(userNickname);
        parcel.writeString(jobGroupName);
        parcel.writeString(recCreateDate);
        parcel.writeString(advantages);
        parcel.writeString(disadvantages);
        parcel.writeByte((byte) (recommended ? 1 : 0));
        parcel.writeInt(helpfulCount);
    }
}




