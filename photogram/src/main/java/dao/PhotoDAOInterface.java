package dao;

import model.Photo;

/**
 * Created by Sergey on 10.11.2016.
 */
public interface PhotoDAOInterface {
    void addPhoto(Photo photo);
    void deletePhoto(Photo photo);
}
