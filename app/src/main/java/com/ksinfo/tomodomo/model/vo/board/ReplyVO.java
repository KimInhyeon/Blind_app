package com.ksinfo.tomodomo.model.vo.board;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class ReplyVO implements Parcelable {
	private final long replyId;
	private final long companyId;
	private final String companyName;
	private final String userNickname;
	private final Long targetReplyId;
	private final String targetNickname;
	private final String replyContents;
	private final char replyBlindFlag;
	private final String recCreateDate;
	private final boolean visible;
	private final String replyFileUrl;
	private final boolean replyRecommended;
	private final int replyRecommendCount;
	private final int nestedCount;
	private final int depth;
	private final boolean writer;

	public ReplyVO(
		@JsonProperty("replyId") long replyId,
		@JsonProperty("companyId") long companyId,
		@JsonProperty("companyName") String companyName,
		@JsonProperty("userNickname") String userNickname,
		@JsonProperty("targetReplyId") Long targetReplyId,
		@JsonProperty("targetNickname") String targetNickname,
		@JsonProperty("replyContents") List<String> replyContents,
		@JsonProperty("replyBlindFlag") char replyBlindFlag,
		@JsonProperty("recCreateDate") String recCreateDate,
		@JsonProperty("visible") boolean visible,
		@JsonProperty("replyFileUrl") String replyFileUrl,
		@JsonProperty("replyRecommended") boolean replyRecommended,
		@JsonProperty("replyRecommendCount") int replyRecommendCount,
		@JsonProperty("nestedCount") int nestedCount,
		@JsonProperty("depth") int depth,
		@JsonProperty("writer") boolean writer
	) {
		this.replyId = replyId;
		this.companyId = companyId;
		this.companyName = companyName;
		this.userNickname = userNickname;
		this.targetReplyId = targetReplyId;
		this.targetNickname = targetNickname;
		if (replyContents.isEmpty()) {
			this.replyContents = null;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(replyContents.get(0));
			for (int i = 1, size = replyContents.size(); i < size; ++i) {
				sb.append("<br>").append(replyContents.get(i));
			}
			this.replyContents = sb.toString();
		}
		this.replyBlindFlag = replyBlindFlag;
		this.recCreateDate = recCreateDate;
		this.visible = visible;
		this.replyFileUrl = replyFileUrl;
		this.replyRecommended = replyRecommended;
		this.replyRecommendCount = replyRecommendCount;
		this.nestedCount = nestedCount;
		this.depth = depth;
		this.writer = writer;
	}

	protected ReplyVO(Parcel in) {
		replyId = in.readLong();
		companyId = in.readLong();
		companyName = in.readString();
		userNickname = in.readString();
		if (in.readByte() == 0) {
			targetReplyId = null;
		} else {
			targetReplyId = in.readLong();
		}
		targetNickname = in.readString();
		replyContents = in.readString();
		replyBlindFlag = (char) in.readInt();
		recCreateDate = in.readString();
		visible = in.readByte() != 0;
		replyFileUrl = in.readString();
		replyRecommended = in.readByte() != 0;
		replyRecommendCount = in.readInt();
		nestedCount = in.readInt();
		depth = in.readInt();
		writer = in.readByte() != 0;
	}

	public static final Creator<ReplyVO> CREATOR = new Creator<ReplyVO>() {
		@Override
		public ReplyVO createFromParcel(Parcel in) {
			return new ReplyVO(in);
		}

		@Override
		public ReplyVO[] newArray(int size) {
			return new ReplyVO[size];
		}
	};

	public long getReplyId() {
		return replyId;
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

	public Long getTargetReplyId() {
		return targetReplyId;
	}

	public String getTargetNickname() {
		return targetNickname;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public char getReplyBlindFlag() {
		return replyBlindFlag;
	}

	public String getRecCreateDate() {
		return recCreateDate;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getReplyFileUrl() {
		return replyFileUrl;
	}

	public boolean isReplyRecommended() {
		return replyRecommended;
	}

	public int getReplyRecommendCount() {
		return replyRecommendCount;
	}

	public int getNestedCount() {
		return nestedCount;
	}

	public int getDepth() {
		return depth;
	}

	public boolean isWriter() {
		return writer;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeLong(replyId);
		parcel.writeLong(companyId);
		parcel.writeString(companyName);
		parcel.writeString(userNickname);
		if (targetReplyId == null) {
			parcel.writeByte((byte) 0);
		} else {
			parcel.writeByte((byte) 1);
			parcel.writeLong(targetReplyId);
		}
		parcel.writeString(targetNickname);
		parcel.writeString(replyContents);
		parcel.writeInt((int) replyBlindFlag);
		parcel.writeString(recCreateDate);
		parcel.writeByte((byte) (visible ? 1 : 0));
		parcel.writeString(replyFileUrl);
		parcel.writeByte((byte) (replyRecommended ? 1 : 0));
		parcel.writeInt(replyRecommendCount);
		parcel.writeInt(nestedCount);
		parcel.writeInt(depth);
		parcel.writeByte((byte) (writer ? 1 : 0));
	}
}