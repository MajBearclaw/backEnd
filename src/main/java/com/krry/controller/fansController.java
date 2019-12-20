package com.krry.controller;

import com.krry.entity.DUser;
import com.krry.entity.Fan;
import com.krry.entity.User;
import com.krry.entity.result;
import com.krry.repository.FansRepository;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value = "/api")
public class fansController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FansRepository fansRepository;
    //关注
    //usernameOne我 usernameTwo明星
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/followOne")
    public result followOne(@RequestBody DUser requestUser){
        //
        User usr = userRepository.findByUsername(requestUser.getUsernameOne());
        User us2 = userRepository.findByUsername(requestUser.getUsernameTwo());
        //User usr = userRepository.findByUsername("yudan");
        //User us2 = userRepository.findByUsername("yudan2");
        usr.setFollowNumber(usr.getFollowNumber()+1);
        us2.setFansNumber(us2.getFansNumber()+1);

        Fan of = fansRepository.findFanByStarAndFans(us2.getUsername(),usr.getUsername());
        if(of != null)
            return new result(400,"already follow",null);

        Fan nf = new Fan(usr.getUsername(),us2.getUsername());
        userRepository.save(us2);
        userRepository.save(usr);
        fansRepository.save(nf);

        return new result(200,"follow success",null);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/dfollowOne")
    //我取关某人
    public result DFLWONE(@RequestBody DUser requestUser){
        //
        User usr = userRepository.findByUsername(requestUser.getUsernameOne());
        User us2 = userRepository.findByUsername(requestUser.getUsernameTwo());
        usr.setFollowNumber(usr.getFollowNumber()-1);
        us2.setFansNumber(us2.getFansNumber()-1);
        Fan of = fansRepository.findFanByStarAndFans(us2.getUsername(),usr.getUsername());
        if(of == null)
            System.out.println("dudulu");
        fansRepository.delete(of);
        userRepository.save(us2);
        userRepository.save(usr);
        return new result(200,"no longer follow him",null);

    }

    //200关注 400没有
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/DoIFollowOne")
	public result havIOne(@RequestBody DUser requestUser){
//        //
//        User ussr = userRepository.findByUsername("yudan");
//        User uss2 = userRepository.findByUsername("yudan2");

        User usr = userRepository.findByUsername(requestUser.getUsernameOne());
        User us2 = userRepository.findByUsername(requestUser.getUsernameTwo());

		return new result(400,"not already",null);
	}

	//返回粉丝
	//username -> 我的粉丝
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/returnMyfans")
	public result retFans(@RequestBody User user){
//        //
//        User usr = userRepository.findByUsername("yudan");
//        User us2 = userRepository.findByUsername("yudan2");
        //Fan nf = new Fan(usr.getUsername(),us2.getUsername());
		String username = HtmlUtils.htmlEscape(user.getUsername());
		//List<Fan> _fans = fansRepository.findFansByStar(username);
        List<Fan> _fans = fansRepository.findFansByStar(username);
		List<User> _rfn = new ArrayList<>();
		if(_fans != null && _fans.size() != 0){
		    for(int i=0;i<_fans.size();i++)
		        _rfn.add(userRepository.findByUsername(_fans.get(i).getFans()));
            return new result(200,"get it",_rfn);

        }
		return new result(400,"no fans",null);
	}

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/fansNumber")
    public result retFN(@RequestBody User user){
        String _username = HtmlUtils.htmlEscape(user.getUsername());
        User usr = userRepository.findByUsername(_username);
        int fansNumber = usr.getFansNumber();
        return new result(200,"success",fansNumber);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/followNumber")
    public result flwN(@RequestBody User user){
        String _username = HtmlUtils.htmlEscape(user.getUsername());
        User usr = userRepository.findByUsername(_username);
        int flwNumber = usr.getFollowNumber();
        return new result(200,"success",flwNumber);
    }
	//返回关注
    //username -> 我的关注
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/returnMyfollows")
	public result retFlw(@RequestBody User user){
        //
        String username = HtmlUtils.htmlEscape(user.getUsername());
        User _usr = userRepository.findByUsername(username);
        List<Fan> _fans = fansRepository.findFansByFans(username);
        List<User> _rst = new ArrayList<>();
        if(_fans != null && _fans.size() != 0){

            for(int i=0;i<_fans.size();i++)
                _rst.add(userRepository.findByUsername(_fans.get(i).getStar()));
                return new result(200,"get it",_rst);

        }
        return new result(400,"no fans",null);
	}

}
