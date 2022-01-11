package com.ksinfo.blind.board.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ImageBlock extends PostBlock {
	public static final class ImageData extends Data {
		private final String url;
		private final String caption;
		private final boolean withBorder;
		private final boolean withBackground;
		private final boolean stretched;

		public ImageData(
			@JsonProperty("url") String url,
			@JsonProperty("caption") String caption,
			@JsonProperty("withBorder") boolean withBorder,
			@JsonProperty("withBackground") boolean withBackground,
			@JsonProperty("stretched") boolean stretched
		) {
			this.url = url;
			this.caption = caption;
			this.withBorder = withBorder;
			this.withBackground = withBackground;
			this.stretched = stretched;
		}

		public String getUrl() {
			return url;
		}

		public String getCaption() {
			return caption;
		}

		public boolean isWithBorder() {
			return withBorder;
		}

		public boolean isWithBackground() {
			return withBackground;
		}

		public boolean isStretched() {
			return stretched;
		}
	}

	public ImageBlock(@JsonProperty("type") String type, @JsonProperty("data") ImageData data) {
		super(type, data);
	}
}