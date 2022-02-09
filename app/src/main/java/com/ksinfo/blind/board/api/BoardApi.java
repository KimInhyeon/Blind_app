package com.ksinfo.blind.board.api;

import com.ksinfo.blind.board.vo.BoardVO;
import com.ksinfo.blind.board.vo.PostAndReplyVO;
import com.ksinfo.blind.board.vo.SearchPostVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoardApi {
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
}