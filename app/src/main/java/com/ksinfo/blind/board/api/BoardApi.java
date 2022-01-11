package com.ksinfo.blind.board.api;

import com.ksinfo.blind.board.vo.BoardVO;
import com.ksinfo.blind.board.vo.PostVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoardApi {
	@GET("board")
	Call<List<BoardVO>> getBoardList();

	@GET("post/1?ajax=true")
	Call<PostVO> getPost();
}