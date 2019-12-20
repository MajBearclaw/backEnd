package com.krry.controller;

import com.krry.entity.*;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

//使用@Controller 才可以返回html页面，使用@ResController 返回的是字符串
@Controller
@RequestMapping(value = "/api")
public class nUserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@CrossOrigin
	@RequestMapping(value = "/login")
	@ResponseBody
	public result login(@RequestBody User requestUser) throws Exception {
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);
		User user = userRepository.findByUsername(username);
		SHAencrypt sha = new SHAencrypt();

		if(user == null)
			return new result(401,"no such account",null);

		if (user.getPassword().equals(sha.encryptSHA(requestUser.getPassword()))) {
			user.setPassword("null");
			return new result(200,"success",user);
		} else {
			return new result(400,"failure");
		}
	}

	@RequestMapping(value="/register")
	@CrossOrigin
	@ResponseBody
	public result resig(@RequestBody handIn requestUser) throws Exception {
		//获取用户和密码
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);
		String pswd = requestUser.getPassword();
		pswd = HtmlUtils.htmlEscape(pswd);
		String spswd = requestUser.getPassword();
		spswd = HtmlUtils.htmlEscape(spswd);

		//根据昵称查询，用户是否存在
		User user = userRepository.findByUsername(username);
		//username的合法性校验
		if(username.length()>8 ||username.length()<4 ||!username.matches("[a-zA-Z0-9]+"))
		    return new result(403,"please input a legal username");

		//若存在
		if(user != null){ //昵称重复
			return new result(401,"username was occupied!");
		}

        //判断密码是否含有同时英文和数字
        String str1 = ".*[a-zA-Z].*";
        String str2 = ".*[0-9].*";
        if(!pswd.matches(str1) || !pswd.matches(str2))
            return new result(405,"password should include both letters and numbers",null);

		//若不存在
		User newUser;
		if(pswd.equals(spswd)) {
		    SHAencrypt sha = new SHAencrypt();
		    pswd = sha.encryptSHA(spswd);
			newUser = new User(username, pswd);
			userRepository.save(newUser);
			return new result(200,"success!");
		}
		else
			return new result(402,"please check your password");


	}


	//找一个名字差不多的列表
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/findByNameLike")
	public result findByName(@RequestBody User requestUser){
		List<User> usrs = userRepository.findByUsernameLike(HtmlUtils.htmlEscape(requestUser.getUsername()));
		System.out.println(requestUser.getUsername());
		System.out.println("No "+usrs.size());
		if(usrs!=null)
			return new result(200,"success",usrs);
		else
			return new result(400,"not found",null);
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping("/findAll")
	public result find(){
		return new result(200,"success",mongoTemplate.findAll(User.class));
	}





}
