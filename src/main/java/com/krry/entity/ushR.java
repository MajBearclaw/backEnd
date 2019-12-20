package com.krry.entity;

public class ushR {
    UserHome ushm;
    int fansNumber;
    int followNumber;

    public ushR() {
    }

    public ushR(UserHome ushm, int fansNumber, int followNumber) {
        this.ushm = ushm;
        this.fansNumber = fansNumber;
        this.followNumber = followNumber;
    }

    public UserHome getUshm() {
        return ushm;
    }

    public void setUshm(UserHome ushm) {
        this.ushm = ushm;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }
}
