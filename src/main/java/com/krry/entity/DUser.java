package com.krry.entity;

public class DUser {
    private String usernameOne;
    private String usernameTwo;

    public DUser() {
    }

    public DUser(String usernameOne , String  usernameTwo) {
        this.usernameOne = usernameOne;
        this.usernameTwo = usernameTwo;
    }

    public String getUsernameTwo() {
        return usernameTwo;
    }

    public void setUsernameTwo(String usernameTwo) {
        this.usernameTwo = usernameTwo;
    }

    public String getUsernameOne() {
        return usernameOne;
    }

    public void setUsernameOne(String usernameOne) {
        this.usernameOne = usernameOne;
    }
}
