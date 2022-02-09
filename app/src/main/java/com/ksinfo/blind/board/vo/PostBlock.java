package com.ksinfo.blind.board.vo;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(name = "paragraph", value = ParagraphBlock.class),
	@JsonSubTypes.Type(name = "image", value = ImageBlock.class)
})
public abstract class PostBlock {
	private final String type;
	private final Data data;

	public static abstract class Data {
		@NonNull
		public abstract String toString();
	}

	public PostBlock(String type, Data data) {
		this.type = type;
		this.data = data;
	}

	public final String getType() {
		return type;
	}

	public final Data getData() {
		return data;
	}
}