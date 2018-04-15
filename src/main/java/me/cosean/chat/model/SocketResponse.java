package me.cosean.chat.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SocketResponse {
    private Set<String> onlineSet ;
    private Map<String, ResponseUser> userSet;
    private Set<String> pendingSet;
    private Set<String> requestSet;
    private Set<String> confirmSet;

    public SocketResponse() {}

    public SocketResponse(Set<String> onlineSet, Map<String, ResponseUser> userSet, Set<String> pendingSet, Set<String> requestSet, Set<String> confirmSet) {
        this.onlineSet = onlineSet;
        this.userSet = userSet;
        this.pendingSet = pendingSet;
        this.requestSet = requestSet;
        this.confirmSet = confirmSet;
    }

    public Set<String> getOnlineSet() {
        return onlineSet;
    }

    public void setOnlineSet(Set<String> onlineSet) {
        this.onlineSet = onlineSet;
    }

    public Map<String, ResponseUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Map<String, ResponseUser> userSet) {
        this.userSet = userSet;
    }

    public Set<String> getPendingSet() {
        return pendingSet;
    }

    public void setPendingSet(Set<String> pendingSet) {
        this.pendingSet = pendingSet;
    }

    public Set<String> getRequestSet() {
        return requestSet;
    }

    public void setRequestSet(Set<String> requestSet) {
        this.requestSet = requestSet;
    }

    public Set<String> getConfirmSet() {
        return confirmSet;
    }

    public void setConfirmSet(Set<String> confirmSet) {
        this.confirmSet = confirmSet;
    }

    @Override
    public String toString() {
        return "{" +
                "\"onlineSet\":" + onlineSet +
                ", \"userSet\":" + userSet +
                ", \"pendingSet\":" + pendingSet +
                ", \"requestSet\":" + requestSet +
                ", \"confirmSet\":" + confirmSet +
                '}';
    }
}
