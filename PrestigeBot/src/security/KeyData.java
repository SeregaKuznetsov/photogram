package security;

import entity.Role;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Сергей on 03.09.2017.
 */
public class KeyData {
    private Map<Key, Role> keys = new HashMap<>();

    public void add(Key key, Role role) {
        keys.put(key, role);
    }

    public void delete(Key key) {
        keys.remove(key);
    }

    public Role getValue(Key key) {
        return keys.get(key);
    }

    public boolean find(Key key) {
        return keys.containsKey(key);
    }

    public Role getValuebyKeyCode(String key) {
        Set<Key> keyset = keys.keySet();
        for (Key el: keyset) {
            if (el.getKey().equals(key)) {
                return getValue(el);
            }
        }
        return null;
    }
}
