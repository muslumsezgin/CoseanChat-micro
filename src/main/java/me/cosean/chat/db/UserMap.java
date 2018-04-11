package me.cosean.chat.db;

import me.cosean.chat.model.User;

import java.util.HashMap;

public class UserMap {
    private static HashMap<String, User> userSet = null;

    public static HashMap<String, User> getInstance() {
        if (userSet == null) {
            userSet = new HashMap<>();
        }
        return userSet;
    }

}
