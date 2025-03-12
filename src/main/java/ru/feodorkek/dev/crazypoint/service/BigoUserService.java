package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.entity.BigoUser;

import java.util.List;

public interface BigoUserService {

    BigoUser createNewUser(String siteId,
                           String displayName,
                           String timeZone,
                           String startStreamMessage,
                           String endStreamMessage,
                           boolean showStreamMessages);

    BigoUser updateUser(String siteId,
                        String displayName,
                        String timeZone,
                        String startStreamMessage,
                        String endStreamMessage,
                        boolean showStreamMessages);

    BigoUser updateShowStreamMessages(String siteId, boolean showStreamMessages);

    BigoUser getUserBySiteId(String siteId);

    void deleteUserBySiteId(String siteId);

    BigoUser saveUser(BigoUser bigoUser);

    void deleteUser(BigoUser bigoUser);

    List<BigoUser> getAllUsers();

}
