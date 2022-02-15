package com.ksinfo.tomodomo.controller.board;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.databinding.BdPostBinding;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.PostVO;
import com.ksinfo.tomodomo.model.vo.board.ReplyVO;
import com.ksinfo.tomodomo.util.GlideImageGetter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class PostActivity extends AppCompatActivity {
    private BdPostBinding binding;
    @Inject BoardInterface boardInterface;
    @Inject @Named("json") MediaType contentType;
    private ReplyAdapter replyAdapter;
    private GlideImageGetter glideImageGetter;
    private PostVO post;
    private List<ReplyVO> replyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = BdPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        glideImageGetter = new GlideImageGetter(this, binding.bdPostContents);

        Intent intent = getIntent();
//        setPostView(intent.getParcelableExtra("post"));
        post = intent.getParcelableExtra("post");
        setPostView(post);
        setReplyView(intent.getParcelableArrayListExtra("replyList"));

        setSupportActionBar(binding.bdPostToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_more_horiz_24);
//        actionBar.setTitle(post.getPostTitle());
//        actionBar.setTitle("post");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
/*
        if (post.isWriter()) {
            menu.removeItem(R.id.reportPost);
        } else {
            menu.removeItem(R.id.editPost);
            menu.removeItem(R.id.deletePost);
        }
*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
            case R.id.editPost:
                return true;
            case R.id.deletePost:
                new AlertDialog.Builder(this)
                               .setMessage(getString(R.string.deleteConfirm))
                               .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {}
                               }).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       deletePost();
                                   }
                               }).create()
                               .show();
                return true;
            case R.id.reportPost:
                return true;
            default:
                Log.d("itemId", String.valueOf(item.getItemId()));

                return super.onOptionsItemSelected(item);
        }
    }

    private void setPostView(PostVO post) {
        binding.bdPostBoardName.setText(post.getBoardTopicName());
        binding.bdPostTitle.setText(post.getPostTitle());
        binding.bdPostCompanyName.setText(post.getCompanyName());
        binding.bdPostUserName.setText(post.getUserNickname());
        binding.bdPostCreateDate.setText(post.getPostCreateDate());
        Spanned postContents;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            postContents = Html.fromHtml(post.getPostContents(), glideImageGetter, null);
        } else {
            postContents = Html.fromHtml(
                    post.getPostContents(), Html.FROM_HTML_MODE_LEGACY, glideImageGetter, null
            );
        }
        binding.bdPostContents.setText(postContents);
        binding.bdPostContents.setMovementMethod(LinkMovementMethod.getInstance());
        binding.bdPostRecommendCount.setText(String.valueOf(post.getPostRecommendCount()));
        binding.bdPostReplyCount.setText(String.valueOf(post.getReplyCount()));
    }

    private void setReplyView(List<ReplyVO> replyList) {
        replyAdapter = new ReplyAdapter(boardInterface, replyList);
        binding.bdPostReplyList.setAdapter(replyAdapter);
/*
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
			this, RecyclerView.VERTICAL, false
		);
		binding.replyList.setLayoutManager(linearLayoutManager);
 */
    }

    private void editPost() {

    }

    private void deletePost() {
        long postId = post.getPostId();
        RequestBody body = RequestBody.create(contentType, Long.toString(postId));
        boardInterface.deletePost(body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d("isSuccessful", String.valueOf(response.isSuccessful()));
//                if (response.isSuccessful()) {
                    final int delete = 3924;
                    Intent intent = new Intent();
                    intent.putExtra("postId", postId);
                    setResult(delete, intent);

                    finish();
//                } else {
//                    Toast.makeText(PostActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT)
//                         .show();
//                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}