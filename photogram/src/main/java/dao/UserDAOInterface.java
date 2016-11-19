package dao;

import model.User;

import java.util.List;

/**
 * Created by Sergey on 10.11.2016.
 */
public interface UserDAOInterface {

    void addUser(User user);
    User read(String username, String password);
    void deleteUser(User user);
    void update(User user);
    User getUserByName(String name);
    User getUserById(int id);
    List<User> getAll();
}
