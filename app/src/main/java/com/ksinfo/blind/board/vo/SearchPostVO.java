package com.ksinfo.blind.board.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public final class SearchPostVO {
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
	private final int postRecommendCount;
	private final int replyCount;
	private final boolean bookmarked;

	public SearchPostVO(
		@JsonProperty("boardId") long boardId,
		@JsonProperty("boardTopicName") String boardTopicName,
		@JsonProperty("postId") long postId,
		@JsonProperty("postTitle") String postTitle,
		@JsonProperty("postContents") ArrayList<PostBlock> postContents,
		@JsonProperty("postFileUrl") String postFileUrl,
		@JsonProperty("companyId") long companyId,
		@JsonProperty("companyName") String companyName,
		@JsonProperty("userNickname") String userNickname,
		@JsonProperty("postCreateDate") String postCreateDate,
		@JsonProperty("postCount") int postCount,
		@JsonProperty("postRecommendCount") int postRecommendCount,
		@JsonProperty("replyCount") int replyCount,
		@JsonProperty("bookmarked") boolean bookmarked
	) throws JsonProcessingException {
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
		this.postRecommendCount = postRecommendCount;
		this.replyCount = replyCount;
		this.bookmarked = bookmarked;
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

	public String getPostContents() {
		if (postContents.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(postContents.get(0).getData());
		for (int i = 1, size = postContents.size(); i < size; ++i) {
			sb.append("<br>").append(postContents.get(i).getData());
		}

		return sb.toString();
	}

	public String getPostFileUrl() {
		return postFileUrl;
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

	public int getPostRecommendCount() {
		return postRecommendCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public boolean isBookmarked() {
		return bookmarked;
	}
}