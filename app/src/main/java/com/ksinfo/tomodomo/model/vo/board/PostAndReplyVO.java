package com.ksinfo.tomodomo.model.vo.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class PostAndReplyVO {
	private final PostVO post;
	private final ArrayList<ReplyVO> replyList;

	public PostAndReplyVO(
		@JsonProperty("post") PostVO post,
		@JsonProperty("replyList") ArrayList<ReplyVO> replyList
	) {
		this.post = post;
		this.replyList = replyList;
	}

	public PostVO getPost() {
		return post;
	}

	public ArrayList<ReplyVO> getReplyList() {
		return replyList;
	}
}