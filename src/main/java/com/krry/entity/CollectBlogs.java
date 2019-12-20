package com.krry.entity;

import org.springframework.data.annotation.Id;

public class CollectBlogs {
    @Id
    private String _id;
    private String username;
    private String bid;

    public CollectBlogs(String username, String bid) {
        this.username = username;
        this.bid = bid;
    }

    public CollectBlogs() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
