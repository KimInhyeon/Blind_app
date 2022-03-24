package com.ksinfo.tomodomo.model.vo.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;
import java.util.List;


public class MemberVO {
    private final long userId;
    private final int userGeneration;
    private final String userNickname;
    private final Date nicknameChangeDate;
    private final int reportedCount;
    private final String userPassword;
    private final String userEmail;
    private final String companyId;
    private final String userAuth;
    private final String companyName;



    public MemberVO(
                @JsonProperty("userId") long userId,
                @JsonProperty("userNickname") String userNickname,
                @JsonProperty("checkNickName") int userGeneration,
                @JsonProperty("nicknameChangeDate") Date nicknameChangeDate,
                @JsonProperty("reportedCount") int reportedCount,
                @JsonProperty("userPassword") String userPassword,
                @JsonProperty("userEmail") String userEmail,
                @JsonProperty("companyId") String companyId,
                @JsonProperty("userAuth") String userAuth,
                @JsonProperty("companyName") String companyName

    ) throws JsonProcessingException {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userGeneration = userGeneration;
        this.nicknameChangeDate = nicknameChangeDate;
        this.reportedCount = reportedCount;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.companyId = companyId;
        this.userAuth = userAuth;
        this.companyName = companyName;

    }

    public long getUserId() {
        return userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public int getUserGeneration() {
        return userGeneration;
    }

    public Date getNicknameChangeDate() {
        return nicknameChangeDate;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public String getCompanyName() {
        return companyName;
    }
}
