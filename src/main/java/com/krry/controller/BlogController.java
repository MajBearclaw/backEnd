package com.krry.controller;


import com.alibaba.fastjson.JSON;
import com.krry.entity.*;
import com.krry.redis.BlogRedis;
import com.krry.repository.BlogRepository;
import com.krry.repository.ReviewRepository;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.util.StringUtils;
import org.unbescape.html.HtmlEscape;

import java.util.*;

@Controller
@RequestMapping(value = "/api")
public class BlogController  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blgRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReviewRepository revRepository;

    @Autowired
    private BlogRedis blogRedis;
    public static final String ALL_BLOG_REDIS = "allblogredis";

//    //发布
//    //要求  username；content；title；_id
//    @CrossOrigin
//    @ResponseBody
//    @RequestMapping(value = "/post")
//    public result postBlog(@RequestBody Blog blg){
//        String bid = HtmlUtils.htmlEscape(blg.get_id());
//        String _username = blg.getUsername();
//        _username = HtmlUtils.htmlEscape(_username);
//        Object _content = blg.getContent();
//        String _title = blg.getTitle();
//        _title = HtmlUtils.htmlEscape(_title);
//        String _type =HtmlUtils.htmlEscape(blg.getType());
//        if(bid.equals("goudan")){
//            Blog _blg = new Blog(_username,_title,_content,_type);
//            User user = userRepository.findByUsername(_username);
//            blg.setUid(user.get_id());
//            blgRepository.save(_blg);
//            return  new result(200,"post it",null);
//        }
//        Blog _blg = blgRepository.findBlogBy_id(bid);
//        _blg.setContent(_content);
//        _blg.setType(_type);
//        _blg.setTitle(_title);
//        blgRepository.save(_blg);
//
//        return  new result(200,"edit it",null);
//    }

    //撤回
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/dpost")
    public result dpostBlog(@RequestBody Blog blg){
        String _uid = blg.getUid();
        Blog _blg = blgRepository.findBlogBy_id(HtmlUtils.htmlEscape(blg.get_id()));
        if (_blg.getUid().equals(_uid)){
            blgRepository.removeBy_id(_blg.get_id());
            return new result(200,"success",null);
        }
        return new result(400,"failure",null);
    }

    //找到一篇
    //要求 _id;
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forOne")
    public result oneBlog(@RequestBody Blog blg){
        String _bid = HtmlUtils.htmlEscape(blg.get_id());
        Blog _blg = blgRepository.findBlogBy_id(_bid);
        if(_blg != null)
        return new result(200,"get one",_blg);
        else
            return new result(400,"no such one",null);
    }

    /*//返回一类
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forTypeOne")
    public result oneTypeBlog(@RequestBody String type){
        String _type = HtmlUtils.htmlEscape(type);
        List<Blog> _blgs = blgRepository.findBlogsBySubject(_type);
        Collections.reverse(_blgs);
        if(_blgs != null)
            return new result(200,"get one",_blgs);
        else
            return new result(400,"no such one",null);
    }*/

//_id uid
    //看看有没有收藏 200 还没 400有
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/sureCollectOne")
    public result havIOne(@RequestBody Blog blg){
        User usr = userRepository.findByUsername(blg.getUid());
        Blog _blg = blgRepository.findBlogBy_id(blg.get_id());
        List<Blog> usr_cl = blgRepository.findBlogsByUsername(usr.getUsername());
        for(int i=0;i<usr_cl.size();i++){
            if(usr_cl.get(i).equals(_blg))
                return new result(400,"already flw",null);
        }
        return new result(200,"not already",null);
    }


    //返回某人的发布
    //username
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/myBlogs")
    public result myPost(@RequestBody User user){
        User _user = userRepository.findByUsername(HtmlUtils.htmlEscape(user.getUsername()));
        List<Blog> blgs = blgRepository.findBlogsByUsername(_user.getUsername());
        Collections.reverse(blgs);
        if(blgs == null)
            return  new result(400,"nothing here",null);
        return new result(200,"ClBlogs",blgs);

    }


    //模糊查找api；
    //要求 title；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchTitle")
    public result searchForSome(@RequestBody Blog blg){
        String _title = HtmlUtils.htmlEscape(blg.getTitle());
        List<Blog> blgs = blgRepository.findByTitleLike(_title);
        Collections.reverse(blgs);
        if(blgs == null || blgs.size() == 0)
            return new result(400,"no thing found",null);
        return  new result(200,"search it",blgs);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchContent")
    public result searchForSomeC(@RequestBody Blog blg){
        String _content = HtmlUtils.htmlEscape(blg.getTitle());
        List<Blog> blgs = blgRepository.findByAbstractsLike(_content);
        List<Blog> blgt = blgRepository.findByTitleLike(_content);
        for(int i = 0;i < blgt.size();i++){
            int j;
            for(j = 0;j < blgs.size(); j++){
                if(blgs.get(j).get_id().equals(blgt.get(i).get_id()))
                    break;
            }
            if(j == blgs.size()){
                blgs.add(blgt.get(i));
            }
        }
         Collections.reverse(blgs);
        if(blgs.size() == 0)
            return  new result(400,"not found",blgs);
        System.out.println("search it");
        return  new result(200,"search it",blgs);
    }


    //查找一类；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchType")
    public result searchForOneType(@RequestBody Find blg){
        String _type = HtmlUtils.htmlEscape(blg.getType());
        List <String> s = new ArrayList<>();
        s.add(_type);
        List<Blog> blgs = blgRepository.findBlogsBySubjectLike(s);
        Collections.reverse(blgs);
        return  new result(200,"search it",blgs);
    }


    //返回最新的10篇
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/recommend")
    public result reC(){
        List<Blog> blgs = blgRepository.findAll();
        List<Blog> _blgs;
        Collections.sort(blgs);
        if(blgs.size() - 10 > 0){
            _blgs = blgs.subList(0,10);
            return new result(200,"success",_blgs);
        }
        else
            return new result(201,"success!",blgs);
    }

    //返回list 所有博文；
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/findAllBlog")
    public result find(){
        return new result(200,"success",mongoTemplate.findAll(Blog.class));
    }



    // 返回blog的所有评论
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/blogReview")
    public result showReview(@RequestBody Blog tblg){
        List<Review> reviews = revRepository.findReviewsByBid(tblg.get_id());
        Collections.reverse(reviews);
        if(reviews.size() == 0)
            return  new result(400,"not found",reviews);
        return  new result(200,"get reviews",reviews);
    }

    //发布评论
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/postReview")
    //username content bid
    public result postReview(@RequestBody newReview nr){
        String bid = HtmlUtils.htmlEscape(nr.getBid());
        Blog blg = blgRepository.findBlogBy_id(bid);
        String username = HtmlUtils.htmlEscape(nr.getUsername());
        String content = HtmlUtils.htmlEscape(nr.getContent());
        Review Rev = new Review(content,username,bid);
        revRepository.save(Rev);
        System.out.println("bid "+bid);
        revRepository.findReviewsByBid(bid);
        //System.out.println(revRepository.findReviewsByBid(bid));
        //System.out.println(blg.getreviews());
        return  new result(200,"return reviews",revRepository.findReviewsByBid(bid));
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forOneBlog")
    public result oneBlogTitle(@RequestBody Blog blg){
        String _authorname = HtmlUtils.htmlEscape(blg.getUsername());
        String _blt = HtmlUtils.htmlEscape(blg.getTitle());
        List<Blog> _blg = blgRepository.findBlogsByTitle(_blt);
        for (int i = 0; i < _blg.size(); i++) {
            for (int j = 0; j < _blg.get(i).getAuthor().size(); j++) {
                if (_authorname.equals(_blg.get(i).getAuthor().get(j)))
                    return new result(200, "success", _blg.get(i));
            }
        }
        return new result(400,"no such one",null);
    }


}
