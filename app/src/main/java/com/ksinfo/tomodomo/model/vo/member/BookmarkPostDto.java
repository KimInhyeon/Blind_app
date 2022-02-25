package com.ksinfo.tomodomo.model.vo.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ksinfo.tomodomo.model.vo.board.PostBlock;

import java.util.List;

public final class BookmarkPostDto {
    private final long boardId;
    private final String boardTopicName;
    private final long postId;
    private final String postTitle;
    private final List<PostBlock> postContents;
    private final String postFileUrl;
    private final long companyId;
    private final String companyName;
    private final String userNickname;
    private final String postCreateDate;
    private final int postCount;
    private final boolean recommended;
    private final int postRecommendCount;
    private final int replyCount;

    public BookmarkPostDto(@JsonProperty("boardId")              long boardId,
                           @JsonProperty("boardTopicName")       String boardTopicName,
                           @JsonProperty("postId")               long postId,
                           @JsonProperty("postTitle")            String postTitle,
                           @JsonProperty("postContents")         List<PostBlock> postContents,
                           @JsonProperty("postFileUrl")          String postFileUrl,
                           @JsonProperty("companyId")            long companyId,
                           @JsonProperty("companyName")          String companyName,
                           @JsonProperty("userNickname")         String userNickname,
                           @JsonProperty("postCreateDate")       String postCreateDate,
                           @JsonProperty("postCount")            int postCount,
                           @JsonProperty("recommended")          boolean recommended,
                           @JsonProperty("postRecommendCount")   int postRecommendCount,
                           @JsonProperty("replyCount")           int replyCount ){

        this.boardId = boardId;
        this.boardTopicName = boardTopicName;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContents = postContents;
        this.postFileUrl = postFileUrl;
        this.companyId = companyId;
        this.companyName = companyName;
        this.userNickname = userNickname;
        this.postCreateDate = postCreateDate;
        this.postCount = postCount;
        this.recommended = recommended;
        this.postRecommendCount = postRecommendCount;
        this.replyCount = replyCount;
    }

    public long getBoardId() { return boardId; }

    public String getBoardTopicName() { return boardTopicName; }

    public long getPostId() { return postId; }

    public String getPostTitle() { return postTitle; }

    public List<PostBlock> getPostContents() { return postContents; }

    public String getPostFileUrl() { return postFileUrl; }

    public long getCompanyId() { return companyId; }

    public String getCompanyName() { return companyName; }

    public String getUserNickname() { return userNickname; }

    public String getPostCreateDate() { return postCreateDate; }

    public int getPostCount() { return postCount; }

    public boolean isRecommended() { return recommended; }

    public int getPostRecommendCount() { return postRecommendCount; }

    public int getReplyCount() { return replyCount; }
}
