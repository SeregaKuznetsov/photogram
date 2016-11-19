package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by Сергей on 09.11.2016.
 */
public class User {

    private int id;
    private String name;
    private String password;
    private int age;
    private List<Post> posts;
    private List<Subscriber> subscribers;
    private List<Subscribtion> subscribtions;
    private String email;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String st)
    {

        String password = DigestUtils.md5Hex(st);

        this.password=password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Subscribtion> getSubscribtions() {
        return subscribtions;
    }

    public void setSubscribtions(List<Subscribtion> subscribtions) {
        this.subscribtions = subscribtions;
    }

}
