package me.cosean.chat.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private String nickName;
    private String profilePicture;
    private Set<String> pendingSet;
    private Set<String> requestSet;
    private Set<String> confirmSet;

    public User() {
        this.pendingSet = new HashSet<>();
        this.requestSet = new HashSet<>();
        this.confirmSet = new HashSet<>();
    }

    public Set<String> getRequestSet() {
        return requestSet;
    }

    public void setRequestSet(Set<String> requestSet) {
        this.requestSet = requestSet;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<String> getPendingSet() {
        return pendingSet;
    }

    public void setPendingSet(Set<String> pendingSet) {
        this.pendingSet = pendingSet;
    }

    public boolean addPendingSet(String ip){
        return this.pendingSet.add(ip);
    }

    public boolean deletePendingSet(String ip){
        return this.pendingSet.remove(ip);
    }

    public Set<String> getConfirmSet() {
        return confirmSet;
    }

    public void setConfirmSet(Set<String> confirmSet) {
        this.confirmSet = confirmSet;
    }

    public boolean addConfirmSet(String ip){
        return this.confirmSet.add(ip);
    }

    public boolean deleteConfirmSet(String ip){
        return this.confirmSet.remove(ip);
    }

    public boolean addRequestSet(String ip){
        return this.requestSet.add(ip);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nickName, user.nickName) &&
                Objects.equals(profilePicture, user.profilePicture) &&
                Objects.equals(pendingSet, user.pendingSet) &&
                Objects.equals(requestSet, user.requestSet) &&
                Objects.equals(confirmSet, user.confirmSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nickName, profilePicture, pendingSet, requestSet, confirmSet);
    }
}
