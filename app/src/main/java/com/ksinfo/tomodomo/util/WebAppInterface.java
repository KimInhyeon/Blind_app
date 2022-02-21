package com.ksinfo.tomodomo.util;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.PostBlock;
import com.ksinfo.tomodomo.model.vo.board.PostDto;
import com.ksinfo.tomodomo.model.vo.board.PostUpdateDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebAppInterface {
    private final Activity activity;
    private final BoardInterface boardInterface;
    private final long postId;
    private long boardId;
    private String postTitle;

    public WebAppInterface(Activity activity, BoardInterface boardInterface, long postId) {
        this.activity = activity;
        this.boardInterface = boardInterface;
        this.postId = postId;
    }

    @JavascriptInterface
    public void save(String jsonString) {
        ArrayList<PostBlock> blockList = null;
        try {
            blockList = new ObjectMapper().readValue(jsonString, new TypeReference<ArrayList<PostBlock>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (postId > 0) {
            PostUpdateDto postUpdateDto = new PostUpdateDto(postId, boardId, postTitle, '0', blockList);
            boardInterface.updatePost(postUpdateDto).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        activity.finish();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            PostDto postDto = new PostDto(boardId, postTitle, '0', blockList);
            boardInterface.writePost(postDto).enqueue(new Callback<Long>() {
                @Override
                public void onResponse(Call<Long> call, Response<Long> response) {
                    if (response.isSuccessful()) {
                        Log.d("postId", response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<Long> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
}