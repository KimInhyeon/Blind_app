package com.ksinfo.blind.mypage.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

public final class NoticeVO {
    private final String noticeTitle;
    private final String noticeTypeName;
    private final String userNickname;
    private final String noticeContents;

    private final String noticeWebsiteUrl;
    private final long   postFileId;
    private final String noticeCreateDate;
    private final String noticeUpdateDate;

    public NoticeVO(
        @JsonProperty("noticeTitle") String noticeTitle,
        @JsonProperty("noticeTypeName") String noticeTypeName,
        @JsonProperty("noticeContents") String noticeContents,
        @JsonProperty("userNickname") String userNickname,
        @JsonProperty("noticeWebsiteUrl") String noticeWebsiteUrl,
        @JsonProperty("postFileId") long postFileId,
        @JsonProperty("noticeCreateDate") String noticeCreateDate,
        @JsonProperty("noticeUpdateDate") String noticeUpdateDate
    )
    {
        this.noticeTitle = noticeTitle;
        this.noticeTypeName = noticeTypeName;
        this.userNickname = userNickname;
        this.noticeContents = noticeContents;

        this.noticeWebsiteUrl = noticeWebsiteUrl;
        this.postFileId = postFileId;
        this.noticeCreateDate = noticeCreateDate;
        this.noticeUpdateDate = noticeUpdateDate;
    }

    public String getNoticeTitle(){ return noticeTitle;}
    public String getNoticeTypeName(){return noticeTypeName;}
    public String getuserNickname(){return userNickname;}
    public String getNoticeContents(){return noticeContents;}

    public String getNoticeWebsiteUrl(){ return noticeWebsiteUrl; }
    public long getPostFileId() { return postFileId; }
    public String getNoticeCreateDate(){return noticeCreateDate;}
    public String getNoticeUpdateDate(){return noticeUpdateDate;}

    //test code for data received check.
    @Override
    public String toString(){
        return "noticeList{" +
                "noticeTitle="    + noticeTitle    + "," +
                "noticeTypeName=" + noticeTypeName + "," +
                "userNickname=" + userNickname + "," +
                "noticeContents=" + noticeContents + "," +

                "noticeWebsiteUrl=" + noticeWebsiteUrl + "," +
                "postFileId=" + postFileId + "," +
                "noticeCreateDate=" + noticeCreateDate + "," +
                "noticeUpdateDate=" + noticeUpdateDate + "," +
                '}';
    }
}