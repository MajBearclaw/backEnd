package com.krry.entity;

import org.springframework.data.annotation.Id;

public class Fan {
    @Id
    private String _id;
    private String star;
    private String fans;

    public Fan(String fans,String star) {
        this.fans = fans;
        this.star = star;
    }

    public Fan() {
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
