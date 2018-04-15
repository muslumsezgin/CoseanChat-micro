package me.cosean.chat.db;

import me.cosean.chat.model.ResponseUser;
import me.cosean.chat.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserMap {
    private static HashMap<String, User> userSet = null;

    public static HashMap<String, User> getInstance() {
        if (userSet == null) {
            userSet = new HashMap<>();
        }
        return userSet;
    }

    public static HashMap<String, ResponseUser> getUserWitoutSets() {
        HashMap<String, ResponseUser> newUsers = new HashMap<>();
        if (Objects.nonNull(UserMap.getInstance()))
            UserMap.getInstance().forEach((key, value) -> {
                ResponseUser u = new ResponseUser(value.getNickName(), value.getProfilePicture());
                newUsers.put(key, u);
            });
        return newUsers;
    }

}
