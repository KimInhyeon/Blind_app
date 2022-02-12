package com.ksinfo.blind.board.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.blind.TomodomoApplication;
import com.ksinfo.blind.board.adapter.SearchPostAdapter;
import com.ksinfo.blind.board.api.BoardApi;
import com.ksinfo.blind.board.vo.BoardVO;
import com.ksinfo.blind.databinding.SearchPostBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class SearchPostActivity extends AppCompatActivity {
	private SearchPostBinding binding;
	@Inject BoardApi boardApi;
	private SearchPostAdapter searchPostAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = SearchPostBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		TomodomoApplication tomodomoApplication = ((TomodomoApplication) getApplication());
		tomodomoApplication.getApplicationComponent().inject(this);

		searchPostAdapter = new SearchPostAdapter(boardApi);
		binding.postList.setAdapter(searchPostAdapter);
/*
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
            getApplicationContext(), RecyclerView.VERTICAL, false
		);
		binding.postList.setLayoutManager(linearLayoutManager);
 */
		getBoardSliderList();
		setSortSpinner();
		initScrollListener();
	}

	private void getBoardSliderList() {
		boardApi.getBoardSliderList().enqueue(new Callback<ArrayList<BoardVO>>() {
			@Override
			public void onResponse(
				@NonNull Call<ArrayList<BoardVO>> call,
				@NonNull Response<ArrayList<BoardVO>> response
			) {
				if (response.isSuccessful()) {
					List<BoardVO> boardList = response.body();
					for (BoardVO board : boardList) {
						Button boardButton = new Button(getApplicationContext());
						boardButton.setText(board.getBoardTopicName());
						binding.boardList.addView(boardButton);

						boardButton.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								searchPostAdapter.setBoardId(board.getBoardId());
								searchPostAdapter.setPostId(0L);
								searchPostAdapter.getPostList();
							}
						});
					}
				}
			}

			@Override
			public void onFailure(@NonNull Call<ArrayList<BoardVO>> call, @NonNull Throwable t) {
				t.printStackTrace();
			}
		});
	}

	private void setSortSpinner() {
		binding.sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				searchPostAdapter.setPostId(0L);
				searchPostAdapter.setSort(position == 0 ? "date" : "recommend");
				searchPostAdapter.getPostList();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}

	private void initScrollListener() {
		binding.postList.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				LinearLayoutManager linearLayoutManager =
					(LinearLayoutManager) recyclerView.getLayoutManager();
				if (linearLayoutManager.findLastVisibleItemPosition() == searchPostAdapter.getItemCount() - 1) {
					searchPostAdapter.addPostList();
				}
			}
		});
	}
}