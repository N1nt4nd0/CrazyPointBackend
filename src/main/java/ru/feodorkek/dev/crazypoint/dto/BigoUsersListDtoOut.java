package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class BigoUsersListDtoOut {

    private final List<BigoUserDtoOut> bigoUsers;
    private final int bigoUsersTotal;

    @JsonCreator
    public BigoUsersListDtoOut(
            @JsonProperty("bigoUsers") List<BigoUserDtoOut> bigoUsers,
            @JsonProperty("bigoUsersTotal") int bigoUsersTotal) {
        this.bigoUsersTotal = bigoUsersTotal;
        this.bigoUsers = bigoUsers;
    }

}
