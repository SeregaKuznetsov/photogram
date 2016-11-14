package dao;

import model.Post;

/**
 * Created by Sergey on 10.11.2016.
 */
public interface PostDAOInterface {
    void addPost(Post post);
    void deletePost(Post post);
}
