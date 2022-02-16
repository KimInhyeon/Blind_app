package com.ksinfo.tomodomo.model.itf;

import com.ksinfo.tomodomo.model.vo.board.BoardVO;
import com.ksinfo.tomodomo.model.vo.board.PostVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoardInterface {
	@GET("board")
	Call<List<BoardVO>> getBoardList();

	@GET("post/1?ajax=true")
	Call<PostVO> getPost();
}