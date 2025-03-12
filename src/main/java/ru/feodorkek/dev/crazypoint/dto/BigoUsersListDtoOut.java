package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

import java.util.List;

@Data
public class BigoUsersListDtoOut {

    private final List<BigoUserDtoOut> bigoUsers;
    private final int bigoUsersTotal;

}
