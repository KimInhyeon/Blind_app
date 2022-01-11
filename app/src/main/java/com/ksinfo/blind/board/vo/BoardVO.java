package com.ksinfo.blind.board.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class BoardVO {
	private final long boardId;
	private final String boardTopicName;

	public BoardVO(
		@JsonProperty("boardId") long boardId,
		@JsonProperty("boardTopicName") String boardTopicName
	) {
		this.boardId = boardId;
		this.boardTopicName = boardTopicName;
	}

	public long getBoardId() {
		return boardId;
	}

	public String getBoardTopicName() {
		return boardTopicName;
	}

	//창주씨 시연시의 코드.
	@Override
	public String toString(){
		return "BoardVO{" +
				"boardId=" + boardId +
				", boardTopicName='" + boardTopicName + '\'' +
				'}';
	}


}