package com.ksinfo.blind.board.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public final class PostVO {
	private final long boardId;
	private final String boardTopicName;
	private final long postId;
	private final String postTitle;
	private final List<PostBlock> postContents;
	private final long companyId;
	private final String companyName;
	private final String userNickname;
	private final String postCreateDate;
	private final int postCount;
	private final boolean postRecommended;
	private final int postRecommendCount;
	private final boolean bookmarked;
	private final boolean writer;
	private final int replyCount;

	public PostVO(
		@JsonProperty("boardId") long boardId,
		@JsonProperty("boardTopicName") String boardTopicName,
		@JsonProperty("postId") long postId,
		@JsonProperty("postTitle") String postTitle,
		@JsonProperty("postContents") List<PostBlock> postContents,
		@JsonProperty("companyId") long companyId,
		@JsonProperty("companyName") String companyName,
		@JsonProperty("userNickname") String userNickname,
		@JsonProperty("postCreateDate") String postCreateDate,
		@JsonProperty("postCount") int postCount,
		@JsonProperty("postRecommended") boolean postRecommended,
		@JsonProperty("postRecommendCount") int postRecommendCount,
		@JsonProperty("bookmarked") boolean bookmarked,
		@JsonProperty("writer") boolean writer,
		@JsonProperty("replyCount") int replyCount
	) throws JsonProcessingException {
		this.boardId = boardId;
		this.boardTopicName = boardTopicName;
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.companyId = companyId;
		this.companyName = companyName;
		this.userNickname = userNickname;
		this.postCreateDate = postCreateDate;
		this.postCount = postCount;
		this.postRecommended = postRecommended;
		this.postRecommendCount = postRecommendCount;
		this.bookmarked = bookmarked;
		this.writer = writer;
		this.replyCount = replyCount;
	}

	public long getBoardId() {
		return boardId;
	}

	public String getBoardTopicName() {
		return boardTopicName;
	}

	public long getPostId() {
		return postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public List<PostBlock> getPostContents() {
		return postContents;
	}

	public long getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getPostCreateDate() {
		return postCreateDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public boolean isPostRecommended() {
		return postRecommended;
	}

	public int getPostRecommendCount() {
		return postRecommendCount;
	}

	public boolean isBookmarked() {
		return bookmarked;
	}

	public boolean isWriter() {
		return writer;
	}

	public int getReplyCount() {
		return replyCount;
	}
}