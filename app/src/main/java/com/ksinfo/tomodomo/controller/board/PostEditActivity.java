package com.ksinfo.tomodomo.controller.board;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.databinding.BdPostEditBinding;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.BoardVO;
import com.ksinfo.tomodomo.model.vo.board.PostEditFileVO;
import com.ksinfo.tomodomo.model.vo.board.PostVO;
import com.ksinfo.tomodomo.util.WebAppInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEditActivity extends AppCompatActivity {
    private BdPostEditBinding binding;
    @Inject BoardInterface boardInterface;
    @Inject @Named("boardList") List<BoardVO> boardList;
    @Inject ArrayAdapter<BoardVO> boardAdapter;
    @Inject @Named("json") MediaType contentType;
    private PostVO post;
    private long postFileSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = BdPostEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        if (getIntent().hasExtra("post")) {
            post = getIntent().getParcelableExtra("post");
        }
        binding.bdPostEditView.getSettings().setJavaScriptEnabled(true);
        binding.bdPostEditView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (post == null) {
                    binding.bdPostEditView.loadUrl("javascript:writeInit();");
                } else {
                    binding.bdPostEditView.loadUrl("javascript:editInit(" + post.getPostBlocks() + ");");
                }
            }
        });
        WebAppInterface webAppInterface = new WebAppInterface(this, boardInterface, post == null ? 0L : post.getPostId());
        binding.bdPostEditView.addJavascriptInterface(webAppInterface, "Android");
        binding.bdPostEditView.loadUrl("file:///android_asset/post.html");

        getPostFileList();

        binding.bdPostEditBoardList.setAdapter(boardAdapter);

        binding.bdPostEditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.bdPostEditRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PostEditActivity.this)
                               .setMessage(getString(R.string.saveConfirm))
                               .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {}
                               }).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       int titleLength = binding.bdPostEditPostTitle.getText().length();
                                       if (titleLength == 0) {
                                           Toast.makeText(
                                               PostEditActivity.this
                                             , getString(R.string.inputTitle)
                                             , Toast.LENGTH_SHORT
                                           ).show();
                                           return;
                                       }
                                       if (titleLength > 200) {
                                           Toast.makeText(
                                               PostEditActivity.this
                                             , getString(R.string.tooLongTitle)
                                             , Toast.LENGTH_SHORT
                                           ).show();
                                           return;
                                       }
                                       webAppInterface.setBoardId(binding.bdPostEditBoardList.getSelectedItemId());
                                       webAppInterface.setPostTitle(binding.bdPostEditPostTitle.getText().toString());
                                       binding.bdPostEditView.loadUrl("javascript:save();");
                                   }
                               }).create()
                               .show();
            }
        });

        binding.bdPostEditPostTitle.setText(post.getPostTitle());
    }

    private void getPostFileList() {
        boardInterface.getPostFileList(post.getPostId()).enqueue(new Callback<ArrayList<PostEditFileVO>>() {
            @Override
            public void onResponse(Call<ArrayList<PostEditFileVO>> call, Response<ArrayList<PostEditFileVO>> response) {
                if (response.isSuccessful()) {
                    List<PostEditFileVO> postEditFileList = response.body();
                    for (PostEditFileVO postFile : postEditFileList) {
                        postFileSize += postFile.getPostFileSize();
                        //TODO: イメージ削除ボタン
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PostEditFileVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}