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

    public boolean validate(String textMsg) {
        Pattern p = Pattern.compile(".+\\.(com|ua|ru)");
        Matcher m = p.matcher(textMsg);
        return m.matches();
    }

    public Entry getEntryFromMsg(String textMsg, String userName) {
        String[] arrayMessage = textMsg.split(",",4);

        String[] dayAndMounth = arrayMessage[0].split("\\.",2);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayAndMounth[0].trim()));
        calendar.set(Calendar.MONTH, Integer.parseInt(dayAndMounth[1].trim()));

        Entry entry = new Entry();
        for (String msg: arrayMessage) {
            System.out.println(msg);
        }
        entry.setDate(calendar);
        entry.setCount(Integer.parseInt(arrayMessage[1].trim()));
        entry.setTime(arrayMessage[2].trim());
        entry.setNotes(arrayMessage[3]);
        entry.setMadeBy(userName);
        return entry;
    }
}
