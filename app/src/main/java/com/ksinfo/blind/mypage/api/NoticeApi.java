package com.ksinfo.blind.mypage.api;

import com.ksinfo.blind.mypage.vo.NoticeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NoticeApi {
    @GET("common/notice/noticeAndroid")
    Call<List<NoticeVO>> getNoticeList();

}
