package com.krry.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class UserHome {
    @Id
    private String _id;
    private String gender;
    private String username;
    private String nickname;
    private Object birth;
    private String address;
    private String work;
    private String position;
    private String intro;
    private String phoneNumber;
    private String Email;

    public UserHome() {
    }

    public UserHome(String gender, String username, String nickname, Object birth, String address, String work, String position, String intro) {
        this.gender = gender;
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.address = address;
        this.work = work;
        this.position = position;
        this.intro = intro;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getBirth() {
        return birth;
    }

    public void setBirth(Object birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhoneNumber(){ return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail(){ return Email; }

    public void setEmail(String Email){ this.Email = Email;}
}
