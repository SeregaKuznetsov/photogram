package dao;

import model.Like;

/**
 * Created by Sergey on 10.11.2016.
 */
public interface LikeDAOInterface {
    void addLike(Like like);
    void deleteLike(Like like);
}
