package com.ksinfo.tomodomo.model.vo.mypage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ModifyProfileVO {
    private final String userNickname;
    private final boolean checkNickName;

    public ModifyProfileVO(
            @JsonProperty("userNickname") String userNickname,
            @JsonProperty("checkNickName") boolean checkNickName

    ) throws JsonProcessingException {
        this.userNickname = userNickname;
        this.checkNickName = checkNickName;
    }

    public String getUserNickName() {
        return userNickname;
    }

    public boolean isCheckNickName() {
        return checkNickName;
    }
}
