package validation;

import security.Key;
import security.KeyData;
import security.KeyGenerator;

import static entity.Role.*;

/**
 * Created by Sergey on 01.09.2017.
 */
public class NewUserValidator {

    private KeyData keyData = new KeyData();

    public Key getKeyForNewUser(String textMsg) {

        KeyGenerator keyGenerator = new KeyGenerator();
        Key key = keyGenerator.createKey();

        switch (textMsg) {
            case "Владелец":
                keyData.add(key, OWNER);
                break;
            case "Администратор":
                keyData.add(key, ADMIN);
                break;
            case "Сотрудник":
                keyData.add(key, WORKER);
                break;
            case "Клиент":
                keyData.add(key, CLIENT);
                break;
            default:
                return null;
        }

        return key;
    }
}
