package com.krry.controller;

import com.krry.entity.*;
import com.krry.repository.AuthorRepository;
import com.krry.repository.FansRepository;
import com.krry.repository.UserRepository;
import com.krry.repository.UsrAuthorRepository;
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
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsrAuthorRepository usrAuthorRepository;

    //查看专家主页
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/authorHome")
    public result authorHome(@RequestBody Author au){
        String _authorname = HtmlUtils.htmlEscape(au.getName());
        Author _author = authorRepository.findAuthorByName(_authorname);
        if(_author != null)
            return new result(200,"success",_author);
        else
            return new result(400,"no such one",null);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forOneAuthor")
    public result oneBlog(@RequestBody Author au){
        String _aid = HtmlUtils.htmlEscape(au.get_id());
        Author _author = authorRepository.findAuthorBy_id(_aid);
        if(_author != null)
            return new result(200,"get one",_author);
        else
            return new result(400,"no such one",null);
    }

    //搜索专家
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchAuthor")
    public result searchAuthor(@RequestBody Author au){
        String _name = HtmlUtils.htmlEscape(au.getName());

        System.out.println(_name);
        List<Author> _author = authorRepository.findByNameLike(_name);
        int num = _author.size();
        if(_author != null)
            return new result(200,"success",_author,num);
        else
            return new result(400,"no such one",null);
    }
    //关注专家
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/follow")
    public result follow(@RequestBody UsrAuthor requestUser){
        //
        String _id = HtmlUtils.htmlEscape(requestUser.getAuthorid());
        String username = HtmlUtils.htmlEscape(requestUser.getUsername());
        Author us2 = authorRepository.findAuthorBy_id(_id);
        User usr = userRepository.findByUsername(username);
        //System.out.println(requestUser.getAuthorid());
        usr.setFollowNumber(usr.getFollowNumber()+1);

        UsrAuthor  of = usrAuthorRepository.findByUsernameAndAuthorid(username,_id);
        if(of != null)
            return new result(400,"already follow",null);
        UsrAuthor nf = new UsrAuthor(usr.getUsername(),us2.get_id());
        userRepository.save(usr);
        usrAuthorRepository.save(nf);

        return new result(200,"follow success",null);
    }
    //取關专家
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/dfollow")
    public result dfollow(@RequestBody UsrAuthor requestUser){
        //
        String _id = HtmlUtils.htmlEscape(requestUser.getAuthorid());
        String username = HtmlUtils.htmlEscape(requestUser.getUsername());
        Author us2 = authorRepository.findAuthorBy_id(_id);
        User usr = userRepository.findByUsername(username);
        //System.out.println(requestUser.getAuthorid());
        usr.setFollowNumber(usr.getFollowNumber()-1);

        UsrAuthor  of = usrAuthorRepository.findByUsernameAndAuthorid(username,_id);
        if(of == null)
            return new result(400,"do not follow before",null);
        userRepository.save(usr);
        usrAuthorRepository.delete(of);
        return new result(200,"no longer follow",null);
    }
    //200关注 400没有
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/DoIFollow")
    public result havIOne(@RequestBody UsrAuthor requestUser){

        String _id = HtmlUtils.htmlEscape(requestUser.getAuthorid());
        String username = HtmlUtils.htmlEscape(requestUser.getUsername());
        UsrAuthor  of = usrAuthorRepository.findByUsernameAndAuthorid(username,_id);
        if(of != null)
            return new result(200,"already follow",null);
        return new result(400,"not already",null);
    }

    //返回我关注的专家
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/returnMyfollow")
    public result MyFollow(@RequestBody UsrAuthor requestUser){
        String username = HtmlUtils.htmlEscape(requestUser.getUsername());
        List<UsrAuthor> of = usrAuthorRepository.findByUsername(username);
        List<Author> authors = new ArrayList<>();
        if(of != null && of.size() > 0){
            for(int i=0;i<of.size();i++) {
                authors.add(authorRepository.findAuthorBy_id(of.get(i).getAuthorid()));
            }
            return new result(200,"get it",authors);
        }
        return new result(400,"no follow",null);

    }
}
