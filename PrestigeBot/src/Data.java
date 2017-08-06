import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 06.08.2017.
 */
public class Data {
    private ArrayList<User> users = new ArrayList<>();
    List<User> getAllUsers() {
        return users;
    }
    void addNewUser (User user) {
        users.add(user);
    }
}
