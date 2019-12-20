package com.krry.entity;

import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Author {
    @Autowired
    private UserRepository userRepository;

    @Id
    private String _id;
    private String name;
    private List<String> pubs;
    private int n_pubs;
    private List<String> tags;
    private String organ;

    public Author() { }
    public Author(String name) {
        this.name = name;
    }
    public Author(String name, List<String> pubs, List<String> tags) {
        this.name = name;
        this.pubs = pubs;
        this.tags = tags;
    }

    public Author(String name, List<String> pubs, int n_pubs, List<String> tags) {
        this.name = name;
        this.pubs = pubs;
        this.n_pubs = n_pubs;
        this.tags = tags;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }


    public List<String> getPubs() {
        return pubs;
    }

    public int getN_pubs() {
        return n_pubs;
    }

    public List<String> getTag() {
        return tags;
    }

    public String getOrgan() { return organ; }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgan(String organ) { this.organ = organ; }

    public void setPubs(List<String> pubs) {
        this.pubs = pubs;
    }

    public void setN_pubs(int n_pubs) {
        this.n_pubs = n_pubs;
    }

    public void setTag(List<String> tags) {
        this.tags = tags;
    }

    public void addPub(String pub){
        pubs.add(pub);
    }
    public void addTag(String tag){
        tags.add(tag);
    }
}
