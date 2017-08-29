package data;

import entity.Role;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 06.08.2017.
 */
public class UserData {
    private ArrayList<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }
    public List<User> getAllWorkers() {
        ArrayList<User> workers = new ArrayList<>();
        for (User user: users) {
            if (user.getRole() == Role.WORKER) {
                workers.add(user);
            }
        }
        return workers;
    }
    public void addNewUser(User user) {
        users.add(user);
    }

    public User getUserById(int id) {

        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
