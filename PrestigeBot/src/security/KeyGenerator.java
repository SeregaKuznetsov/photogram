package security;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sergey on 01.09.2017.
 */
public class KeyGenerator {
    private final int min = 100000; // Минимальное число для диапазона
    private final int max = 999999; // Максимальное число для диапазона

    public Key createKey() {
        Key key = new Key();

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date()); //устанавливаем дату, с которой будет производить операции
        instance.add(Calendar.SECOND, key.liveTime);// прибавляем 60 sec к установленной дате
        Date newDate = instance.getTime(); // получаем измененную дату

        key.setFinishTime(newDate);
        key.setKey(rnd(min, max));

        return key;
    }

    private String rnd(int min, int max)
    {
        max -= min;
        return Integer.toString((int) (Math.random() * ++max) + min);

    }
}
