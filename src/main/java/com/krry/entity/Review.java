package com.krry.entity;

import org.springframework.data.annotation.Id;

public class Review {

    @Id
    private String _id;
    private String content;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    private String username;

    private String bid;

    public Review() {
    }

    public Review(String content, String username, String bid) {
        this.content = content;
        this.username = username;
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
