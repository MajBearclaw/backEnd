package com.krry.controller;

import com.krry.entity.User;
import com.krry.entity.UserHome;
import com.krry.entity.result;
import com.krry.entity.ushR;
import com.krry.repository.UserHomeRepository;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.HashMap;
@Controller
@RequestMapping(value = "/api")
public class UserHomeController {
    @Autowired
    UserHomeRepository userHomeRepository;
    @Autowired
    UserRepository userRepository;

    //编辑用户空间
    // String : location username nickname career signature
    // Date : birthday
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/editUserHome")
    public result editUH(@RequestBody UserHome usrHm){
        //
        String _username = HtmlUtils.htmlEscape(usrHm.getUsername());
        String _nickname = HtmlUtils.htmlEscape(usrHm.getNickname());
        String _work = HtmlUtils.htmlEscape(usrHm.getWork());
        Object _birth = usrHm.getBirth();
        String _gender = HtmlUtils.htmlEscape(usrHm.getGender());
        String _address = HtmlUtils.htmlEscape(usrHm.getAddress());
        String _position = HtmlUtils.htmlEscape(usrHm.getPosition());
        String _intro = HtmlUtils.htmlEscape(usrHm.getIntro());
        UserHome userhm = userHomeRepository.findByUsername(_username);
        if(userhm == null)
            userhm = new UserHome(_gender,_username,_nickname,_birth,_address,_work,_position,_intro);
        userhm.setNickname(_nickname);
        userhm.setAddress(_address);
        userhm.setBirth(_birth);
        userhm.setWork(_work);
        userhm.setGender(_gender);
        userhm.setPosition(_position);
        userhm.setIntro(_intro);


        userHomeRepository.save(userhm);
        return new result(200,"done",null);


    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/visitHome")
    public result viH(@RequestBody User usr){
        //
        String _username = HtmlUtils.htmlEscape(usr.getUsername());
        User _usr = userRepository.findByUsername(_username);
        UserHome ushm = userHomeRepository.findByUsername(_username);
        if(ushm == null) {
            ushm = new UserHome();
            userHomeRepository.save(ushm);
            return new result(400,"nothing here",null);
        }
        ushR ushr = new ushR(ushm,_usr.getFansNumber(),_usr.getFollowNumber());

        return new result(200,"get it",ushr);
    }
}
