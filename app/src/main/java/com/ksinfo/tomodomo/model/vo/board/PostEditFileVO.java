package com.ksinfo.tomodomo.model.vo.board;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PostEditFileVO {
    private final long postFileId;
    private final String postFileOriginName;
    private final long postFileSize;

    public PostEditFileVO(
        @JsonProperty("postFileId") long postFileId
      , @JsonProperty("postFileOriginName") String postFileOriginName
      , @JsonProperty("postFileSize") long postFileSize
    ) {
        this.postFileId = postFileId;
        this.postFileOriginName = postFileOriginName;
        this.postFileSize = postFileSize;
    }

    public long getPostFileId() {
        return postFileId;
    }

    public String getPostFileOriginName() {
        return postFileOriginName;
    }

    public long getPostFileSize() {
        return postFileSize;
    }
}