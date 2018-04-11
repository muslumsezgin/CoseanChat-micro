package me.cosean.chat.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private String nickName;
    private String profilePicture;
    private Set<String> pendingSet;
    private Set<String> confirmSet;

    public User() {
        this.profilePicture = "https://geekyapar.com/wp-content/uploads/2014/10/Scarlett-Johansson-hot-in-white-shirt.jpg";
        this.pendingSet = new HashSet<>();
        this.confirmSet = new HashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(profilePicture, user.profilePicture) &&
                Objects.equals(pendingSet, user.pendingSet) &&
                Objects.equals(confirmSet, user.confirmSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nickName, profilePicture, pendingSet, confirmSet);
    }
}
