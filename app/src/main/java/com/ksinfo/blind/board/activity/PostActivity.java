package com.ksinfo.blind.board.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.TomodomoApplication;
import com.ksinfo.blind.board.adapter.ReplyAdapter;
import com.ksinfo.blind.board.api.BoardApi;
import com.ksinfo.blind.board.vo.PostVO;
import com.ksinfo.blind.board.vo.ReplyVO;
import com.ksinfo.blind.databinding.PostBinding;
import com.ksinfo.blind.util.GlideImageGetter;

import java.util.List;

import javax.inject.Inject;

public final class PostActivity extends AppCompatActivity {
    private PostBinding binding;
    @Inject BoardApi boardApi;
    private ReplyAdapter replyAdapter;
    private GlideImageGetter glideImageGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = ((TomodomoApplication) getApplication());
        tomodomoApplication.getApplicationComponent().inject(this);

        glideImageGetter = new GlideImageGetter(this, binding.postContents);
        setPostView(getIntent().getParcelableExtra("post"));
        setReplyView(getIntent().getParcelableArrayListExtra("replyList"));
    }

    private void setPostView(PostVO post) {
        binding.boardName.setText(post.getBoardTopicName());
        binding.postTitle.setText(post.getPostTitle());
        binding.postCompanyName.setText(post.getCompanyName());
        binding.postUserName.setText(post.getUserNickname());
        binding.postCreateDate.setText(post.getPostCreateDate());
        Spanned postContents;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            postContents = Html.fromHtml(post.getPostContents(), glideImageGetter, null);
        } else {
            postContents = Html.fromHtml(
                post.getPostContents(), Html.FROM_HTML_MODE_LEGACY, glideImageGetter, null
            );
        }
        binding.postContents.setText(postContents);
        binding.postContents.setMovementMethod(LinkMovementMethod.getInstance());
        binding.postRecommendCount.setText(String.valueOf(post.getPostRecommendCount()));
        binding.replyCount.setText(String.valueOf(post.getReplyCount()));
    }

    private void setReplyView(List<ReplyVO> replyList) {
        replyAdapter = new ReplyAdapter(boardApi, replyList);
        binding.replyList.setAdapter(replyAdapter);
/*
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
			this, RecyclerView.VERTICAL, false
		);
		binding.replyList.setLayoutManager(linearLayoutManager);
 */
    }
}