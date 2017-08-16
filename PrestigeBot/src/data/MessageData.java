package data;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 13.08.2017.
 */
public class MessageData {
    private Map<Integer, String> message = new HashMap<>();

    public void add(int user, String word) {
        message.put(user, word);
    }

    public void delete(int userId) {
        message.remove(userId);
    }

    public String getValue(int userId) {
        return message.get(userId);
    }

    public boolean find(int userId) {
        return message.containsKey(userId);
    }
}
