package com.ksinfo.tomodomo.model.vo.board;

import java.util.List;

public final class PostUpdateDto {
    private final long postId;
    private final long boardId;
    private final String postTitle;
    private final char postBlindFlag;
    private final List<PostBlock> postContents;

    public PostUpdateDto(
        long postId
      , long boardId
      , String postTitle
      , char postBlindFlag
      , List<PostBlock> postContents
    ) {
        this.postId = postId;
        this.boardId = boardId;
        this.postTitle = postTitle;
        this.postBlindFlag = postBlindFlag;
        this.postContents = postContents;
    }

    public long getPostId() {
        return postId;
    }

    public long getBoardId() {
        return boardId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public char getPostBlindFlag() {
        return postBlindFlag;
    }

    public List<PostBlock> getPostContents() {
        return postContents;
    }
}