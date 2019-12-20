package com.krry.controller;

import com.krry.entity.headPortrait;
import com.krry.entity.result;
import com.krry.repository.headPortraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;


@Controller
@RequestMapping(value = "/api")
public class headPortraitController {
    @Autowired
    headPortraitRepository hpRepository;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changeHead")
    public result cgHead(@RequestBody headPortrait hp){
        Object _img = hp.getPicture();
        String _username = HtmlUtils.htmlEscape(hp.getUsername());
        headPortrait _hp = hpRepository.findByUsername(_username);
        _hp.setPicture(_img);
        hpRepository.save(_hp);
        return new result(200,"change it",null);

    }

    //picture username post headPortrait
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/postHead")
    public result psHead(@RequestBody headPortrait hp){
        Object _img = hp.getPicture();
        String _username = HtmlUtils.htmlEscape(hp.getUsername());
        headPortrait _hp = hpRepository.findByUsername(_username);
        if(_hp != null)
            hpRepository.delete(_hp);
        _hp = new headPortrait(_img,_username);
        hpRepository.save(_hp);
        return new result(200,"code",null);

    }

    //username return myhead
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/myHead")
    public result myHead(@RequestBody headPortrait hp){
        //Object img = hp.getPicture();
        String _username = HtmlUtils.htmlEscape(hp.getUsername());
        headPortrait _hp = hpRepository.findByUsername(_username);
        if(_hp == null)
            return new result(400,"nothing found",null);
        Object _img = _hp.getPicture();
        return new result(200,"your head",_img);


    }


}
