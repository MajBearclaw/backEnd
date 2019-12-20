package com.krry.entity;

import org.springframework.data.annotation.Id;

public class headPortrait<object> {
    @Id
    private  String _id;
    private object picture;
    private String username;

    public headPortrait(object picture, String username) {
        this.picture = picture;
        this.username = username;
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

    public headPortrait() {
    }

    public headPortrait(object picture) {
        this.picture = picture;
    }

    public object getPicture() {
        return picture;
    }

    public void setPicture(object picture) {
        this.picture = picture;
    }
}