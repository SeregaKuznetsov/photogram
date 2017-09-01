package validation;

import security.Key;
import security.KeyGenerator;

/**
 * Created by Sergey on 01.09.2017.
 */
public class NewUserValidator {

    public Key getKeyForNewUser(String textMsg) {
        KeyGenerator keyGenerator = new KeyGenerator();
        Key key = keyGenerator.createKey();

        return key;
    }
}
