package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

@Data
public class BigoOfficialUserInfo {

    private final int code;
    private final BigoOfficialUserInfoData data;
    private final String msg;

    public boolean isSuccess() {
        return "success".equals(msg);
    }

}
