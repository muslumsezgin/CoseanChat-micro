package me.cosean.chat.db;

import java.util.HashSet;
import java.util.Set;

public class OnlineSet {
    private static Set<String> onlineSet = null;

    public static Set<String> getInstance() {
        if (onlineSet == null) {
            onlineSet = new HashSet<>();
        }
        return onlineSet;
    }
}
