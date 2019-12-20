package com.krry.entity;

public class newReview {
    private String content;
    private String username;
    private String bid;

    public newReview() {
    }

    public newReview(Review review, String username,String bid) {
        this.username = username;
        this.bid = bid;
        this.bid = bid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
