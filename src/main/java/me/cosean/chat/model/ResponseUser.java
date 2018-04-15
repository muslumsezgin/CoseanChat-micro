package me.cosean.chat.model;

public class ResponseUser {
    private String nickName;
    private String profilePicture;

    public ResponseUser(String nickName, String profilePicture) {
        this.nickName = nickName;
        this.profilePicture = profilePicture;
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

    @Override
    public String toString() {
        return "{" +
                "\"nickName\":'" + nickName + '\'' +
                ", \"profilePicture\":'" + profilePicture + '\'' +
                '}';
    }
}
