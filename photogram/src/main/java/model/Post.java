package model;

import java.util.List;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Post {

    private User author;
    private Photo photo;
    private String description;
    private List<Like> likes;
    private List<Comment> comments;

}
