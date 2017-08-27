package validation;

import entity.Entry;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 15.08.2017.
 */
public class NewEntryValidator {

    public Entry getEntryFromMsg(String textMsg, String userName) {
        String[] arrayMessage = textMsg.split(",",5);

        String[] dayAndMounth = arrayMessage[0].split("\\.",2);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayAndMounth[0].trim()));
        calendar.set(Calendar.MONTH, Integer.parseInt(dayAndMounth[1].trim()));

        Entry entry = new Entry();
//        for (String msg: arrayMessage) {
//            System.out.println(msg);
//        }

        entry.setDate(calendar);
        entry.setCount(Integer.parseInt(arrayMessage[1].trim()));
        entry.setTime(arrayMessage[2].trim());
        entry.setCost(arrayMessage[3]);
        if (arrayMessage.length < 5 || arrayMessage[4].isEmpty()) {
            entry.setNotes("Отсутcтвует");
        } else {
            entry.setNotes(arrayMessage[4]);
        }
        entry.setMadeBy(userName);
        return entry;
    }
}
