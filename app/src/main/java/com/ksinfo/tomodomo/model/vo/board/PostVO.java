package com.ksinfo.tomodomo.model.vo.board;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public final class PostVO implements Parcelable {
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
        @JsonProperty("postContents") ArrayList<PostBlock> postContents,
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
    ) {
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

    private PostVO(Parcel in) {
        boardId = in.readLong();
        boardTopicName = in.readString();
        postId = in.readLong();
        postTitle = in.readString();
        postContents = new ArrayList<>();
        in.readTypedList(postContents, PostBlock.CREATOR);
        companyId = in.readLong();
        companyName = in.readString();
        userNickname = in.readString();
        postCreateDate = in.readString();
        postCount = in.readInt();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            postRecommended = in.readByte() != 0;
            postRecommendCount = in.readInt();
            bookmarked = in.readByte() != 0;
            writer = in.readByte() != 0;
        } else {
            postRecommended = in.readBoolean();
            postRecommendCount = in.readInt();
            bookmarked = in.readBoolean();
            writer = in.readBoolean();
        }
        replyCount = in.readInt();
    }

    public static final Creator<PostVO> CREATOR = new Creator<PostVO>() {
        @Override
        public PostVO createFromParcel(Parcel in) {
            return new PostVO(in);
        }

        @Override
        public PostVO[] newArray(int size) {
            return new PostVO[size];
        }
    };

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

    private String getDataHtml(PostBlock.Data data) {
        if (data instanceof ParagraphBlock.ParagraphData) {
            return ((ParagraphBlock.ParagraphData) data).getText();
        } else if (data instanceof ImageBlock.ImageData) {
/*
            StringBuilder sb = new StringBuilder();
            sb.append("<img class=\"ui");
            if (((ImageBlock.ImageData) data).isStretched()) {
                sb.append(" fluid");
            }
            sb.append(" image\" src=\"")
              .append(((ImageBlock.ImageData) data).getUrl())
              .append("\" onclick=\"enlargeImage(this.src)\";>");

            return sb.toString();
*/
            return "<img src=\"" + ((ImageBlock.ImageData) data).getUrl() + "\">";
        }

        return "";
    }

    public String getPostContents() {
        if (postContents.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getDataHtml(postContents.get(0).getData()));
        for (int i = 1, size = postContents.size(); i < size; ++i) {
            sb.append("<br>").append(getDataHtml(postContents.get(i).getData()));
        }

        return sb.toString();
    }

    public String getPostBlocks() {
        if (postContents.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder().append('[');
        sb.append(postContents.get(0));
        for (int i = 1, size = postContents.size(); i < size; ++i) {
            sb.append(',').append(postContents.get(i));
        }

        return sb.append(']').toString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(boardId);
        parcel.writeString(boardTopicName);
        parcel.writeLong(postId);
        parcel.writeString(postTitle);
        parcel.writeTypedList(postContents);
        parcel.writeLong(companyId);
        parcel.writeString(companyName);
        parcel.writeString(userNickname);
        parcel.writeString(postCreateDate);
        parcel.writeInt(postCount);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            parcel.writeByte((byte) (postRecommended ? 1 : 0));
            parcel.writeInt(postRecommendCount);
            parcel.writeByte((byte) (bookmarked ? 1 : 0));
            parcel.writeByte((byte) (writer ? 1 : 0));
        } else {
            parcel.writeBoolean(postRecommended);
            parcel.writeInt(postRecommendCount);
            parcel.writeBoolean(bookmarked);
            parcel.writeBoolean(writer);
        }
        parcel.writeInt(replyCount);
    }
}