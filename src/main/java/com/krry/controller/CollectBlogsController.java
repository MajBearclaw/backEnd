package com.krry.controller;

import com.krry.entity.Blog;
import com.krry.entity.CollectBlogs;
import com.krry.entity.User;
import com.krry.entity.result;
import com.krry.repository.BlogRepository;
import com.krry.repository.CollectBlogsRepository;
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
public class CollectBlogsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CollectBlogsRepository cblgRepository;
    @Autowired
    BlogRepository blgRepository;
    //返回某人的收藏
    //username
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/ClBlogs")
    public result myClBlogs(@RequestBody User user){
        //
        String _username = HtmlUtils.htmlEscape(user.getUsername());
        List<CollectBlogs> cblgs = cblgRepository.findCollectBlogsByUsername(_username);
        if(cblgs == null)
            return  new result(400,"nothing here",null);
        List<Blog> blgs = new ArrayList<>();
        for(int i=0 ;i<cblgs.size();i++){
            blgs.add(blgRepository.findBlogBy_id(cblgs.get(i).getBid()));
        }
        return new result(200,"ClBlogs",blgs);

    }

    //收藏
    //username _id(bid)
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/collect")
    public result collectBlog(@RequestBody Blog blg){
        //
        String _username = HtmlUtils.htmlEscape(blg.getUsername()); ;
        String _id = HtmlUtils.htmlEscape(blg.get_id());
        CollectBlogs cblg = new CollectBlogs();
        cblg.setUsername(_username);
        cblg.setBid(_id);
        cblgRepository.save(cblg);
        Blog _blg = blgRepository.findBlogBy_id(_id);
        _blg.setCollectedTimes(_blg.getCollectedTimes() + 1);
        blgRepository.save(_blg);
        return  new result(200,"get it",null);
    }

    //收藏了？
    //username _id(bid)
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/isCollected")
    public result DcollectBlog(@RequestBody Blog blg){
        String _username = HtmlUtils.htmlEscape(blg.getUsername());
        String _id = HtmlUtils.htmlEscape(blg.get_id());
        List<CollectBlogs> clgs = cblgRepository.findCollectBlogsByUsername(_username);
        if(clgs == null || clgs.size() == 0)
            return  new result(400,"not already",null);
        for(int i=0;i<clgs.size();i++){
            if( clgs.get(i).getBid().equals(_id))
                return new result(200,"you hav cl it",null);
        }
        return  new result(400,"not already",null);
    }



    //取消收藏
    //username _id(bid)
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/dcollect")
    public result dcollectBlog(@RequestBody Blog blg){
        //
        String _username = HtmlUtils.htmlEscape(blg.getUsername());
        String _bid = HtmlUtils.htmlEscape(blg.get_id());
        CollectBlogs clg = cblgRepository.findByUsernameAndBid(_username,_bid);
        Blog _blg = blgRepository.findBlogBy_id(_bid);
        _blg.setCollectedTimes(_blg.getCollectedTimes() - 1);
        blgRepository.save(_blg);
        if(clg == null)
            System.out.println("excuse me?");
        cblgRepository.delete(clg);
        return  new result(200,"get it",null);
    }
}
