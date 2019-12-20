package com.krry.entity;

public class UsrAuthor {
    private String _id;
    private String authorid;
    private String username;


    public UsrAuthor() {
    }

    public UsrAuthor(String usernameOne , String  usernameTwo) {
        this.username = usernameOne;
        this.authorid = usernameTwo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthorid() {
        return authorid;
    }
}
