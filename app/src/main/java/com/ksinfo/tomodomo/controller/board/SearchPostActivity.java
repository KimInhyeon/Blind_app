package com.ksinfo.tomodomo.controller.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.databinding.BdSearchPostBinding;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.BoardVO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public final class SearchPostActivity extends AppCompatActivity {
	private BdSearchPostBinding binding;
	@Inject BoardInterface boardInterface;
	@Inject @Named("boardSliderList") List<BoardVO> boardSliderList;
	private SearchPostAdapter searchPostAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = BdSearchPostBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
		tomodomoApplication.getApplicationComponent().inject(this);

		searchPostAdapter = new SearchPostAdapter(boardInterface);
		binding.bdSearchPostPostList.setAdapter(searchPostAdapter);
/*
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
            getApplicationContext(), RecyclerView.VERTICAL, false
		);
		binding.postList.setLayoutManager(linearLayoutManager);
 */
		createBoardSliderList();
		setSortSpinner();
		initScrollListener();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		Log.d("requestCode", Integer.toString(requestCode));
		Log.d("resultCode", Integer.toString(resultCode));
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void createBoardSliderList() {
		for (BoardVO board : boardSliderList) {
			Button boardButton = new Button(getApplicationContext());
			boardButton.setText(board.getBoardTopicName());
			binding.bdSearchPostBoardList.addView(boardButton);

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

	private void setSortSpinner() {
		binding.bdSearchPostSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
		binding.bdSearchPostPostList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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