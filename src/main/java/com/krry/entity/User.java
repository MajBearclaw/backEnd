package com.krry.entity;

import com.krry.repository.BlogRepository;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类，类名对应的是MongoDB的集合名（表名），若没有，则自动创建
 * @author asusaad
 *
 */
public class User {

	@Autowired
	private BlogRepository blgRepository;
    @Autowired
    private UserRepository userRepository;

    private String _id;
	private String username;
    private String password;
	private int followNumber;
	private int fansNumber;
	private boolean isadmin = false;

	
	public User(){
		
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String cid) {
		this._id = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFollowNumber() {
		return followNumber;
	}
	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}
	public int getFansNumber() {
		return fansNumber;
	}
	public void setFansNumber(int fansNumber) { this.fansNumber = fansNumber;
	}
	public void setIsadmin(boolean b){
		this.isadmin = b;
	}

}
