package com.krry.entity;

import com.krry.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Blog implements Comparable<Blog>{
    @Autowired
    private ReviewRepository revRepository;

    @Id
    private String _id;
    private String uid;
    private String username;
    private String title;
    private Object content;
    private List<String> subject;
    private int collectedTimes;
    private List<String> author;
   //rivate String date;
    private String abstracts;
    private String url;
    private String from;


    public Blog() { }

    public Blog(String title) {
        this.title = title;
    }
    public Blog(String username,String title) {
        this.username = username;
        this.title = title;
        this.collectedTimes = 0;
    }

    public Blog(String username, String title, List<String> subject) {
        this.username = username;
        this.title = title;
        this.subject = subject;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    public void setAbstract(String Abstract){
        this.abstracts = Abstract;
    }
    public String getAbstract(){
        return abstracts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public int getCollectedTimes() {
        return collectedTimes;
    }




    public void setCollectedTimes(int collectedTimes) {
        this.collectedTimes = collectedTimes;
    }
    public void addCollectedTimes() {
        this.collectedTimes += 1;
    }
    public void redCollectedTimes() {
        this.collectedTimes += 1 ;
    }

    public List<String> getAuthor() {
        return author;
    }


    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }


    @Override
    public int compareTo(Blog blg){
        int i = this.title.compareTo(blg.getTitle());
        return -i;
    }
}
