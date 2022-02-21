package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.board.BoardVO;
import com.ksinfo.tomodomo.model.vo.board.PostAndReplyVO;
import com.ksinfo.tomodomo.model.vo.board.PostDto;
import com.ksinfo.tomodomo.model.vo.board.PostEditFileVO;
import com.ksinfo.tomodomo.model.vo.board.PostUpdateDto;
import com.ksinfo.tomodomo.model.vo.board.SearchPostVO;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoardInterface {
    @GET("search/board")
    Call<ArrayList<BoardVO>> getBoardSliderList();

    @GET("search?ajax=true")
    Call<ArrayList<SearchPostVO>> getPostList(
		@Query("searchKeyword") String searchKeyword,
		@Query("sort") String sort,
		@Query("boardId") long boardId,
		@Query("postId") long postId
    );

    @GET("post/{postId}?ajax=true")
    Call<PostAndReplyVO> getPost(@Path("postId") long postId);

//	@GET("reply?ajax=true")
//	Call<List<ReplyVO>> getReplyList(@Query("postId") long postId);

    @GET("board")
    Call<ArrayList<BoardVO>> getBoardList();

    @GET("image/{postId}")
    Call<ArrayList<PostEditFileVO>> getPostFileList(@Path("postId") long postId);

    @HTTP(method = "DELETE", path = "post", hasBody = true)
    Call<Void> deletePost(@Body RequestBody body);

    @PUT("post")
    Call<Void> updatePost(@Body PostUpdateDto postUpdateDto);

    @POST("post")
    Call<Long> writePost(@Body PostDto postDto);
}