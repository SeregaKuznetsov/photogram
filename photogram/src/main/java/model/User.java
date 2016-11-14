package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public void setPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        this.password = sb.toString();

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
