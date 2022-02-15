package com.ksinfo.tomodomo.model.vo.board;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ParagraphBlock extends PostBlock {
	public static final class ParagraphData extends Data {
		private final String text;

		public ParagraphData(@JsonProperty("text") String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		@NonNull
		@Override
		public String toString() {
			return text;
		}
	}

	public ParagraphBlock(
		@JsonProperty("type") String type,
		@JsonProperty("data") ParagraphData data
	) {
		super(type, data);
	}
}