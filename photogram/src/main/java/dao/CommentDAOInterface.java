package dao;

import model.Comment;

/**
 * Created by Sergey on 10.11.2016.
 */
public interface CommentDAOInterface {
    void addComment(Comment comment);
    void deleteComment(Comment comment);
}
