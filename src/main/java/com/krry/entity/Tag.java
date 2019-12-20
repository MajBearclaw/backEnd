package com.krry.entity;

import java.util.List;

public class Tag {
    private String _id;
    private String name;
    private List<String> blgs;

    public Tag(String name) {
        this.name = name;
    }

    public List<String> getBlgs() {
        return blgs;
    }

    public void setBlgs(List<String> blgs) {
        this.blgs = blgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
