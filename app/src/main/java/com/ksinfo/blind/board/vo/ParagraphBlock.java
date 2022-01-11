package com.ksinfo.blind.board.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ParagraphBlock extends PostBlock {
	public static final class ParagraphData extends Data {
		private final StringBuilder text;

		public ParagraphData(@JsonProperty("text") StringBuilder text) {
			this.text = text;
		}

		public String getText() {
			return text.toString();
		}
	}

	public ParagraphBlock(
		@JsonProperty("type") String type, @JsonProperty("data") ParagraphData data) {
		super(type, data);
	}
}