package com.ksinfo.tomodomo.board;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.board.api.BoardApi;
import com.ksinfo.tomodomo.board.vo.BoardVO;
import com.ksinfo.tomodomo.util.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicMainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_main);

		BoardApi boardApi = RetrofitFactory.createJsonRetrofit().create(BoardApi.class);

		boardApi.getBoardList().enqueue(new Callback<List<BoardVO>>() {
			@Override
			public void onResponse(@NonNull Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
				if (response.isSuccessful()) {
				List<BoardVO> boardList = response.body();
				Log.d("boardList", boardList.toString());
				}
			}
			@Override
			public void onFailure(Call<List<BoardVO>> call, Throwable t) {
				t.printStackTrace();
			}
		});

			/*
			@Override
			public void onResponse(Call<PostVO> call, Response<PostVO> response) {
				if (response.isSuccessful()) {
					PostVO post = response.body();
					Log.d("post", post.toString());
				}
			}

			@Override
			public void onFailure(Call<PostVO> call, Throwable t) {

			}
		});
		 */
	}
}