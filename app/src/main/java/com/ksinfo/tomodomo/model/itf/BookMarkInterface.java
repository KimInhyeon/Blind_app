package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.member.BookmarkPostVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookMarkInterface {
    @GET("bookmark/getMyPostListAndroid")
    Call<List<BookmarkPostVO>> getMyPostList(@Query("userId") Long userId,
                                             @Query("offset") int  offset);
}
