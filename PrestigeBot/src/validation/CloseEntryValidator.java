package validation;

import data.EntryData;
import entity.Entry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergey on 01.09.2017.
 */
public class CloseEntryValidator {
    private EntryData entryData = new EntryData();

    public String closeEntry(String textMsg) {
        String answer = "";
        int entryId;
        try {
           entryId = Integer.parseInt(textMsg);
        } catch (Exception e) {
            answer = "Не верный формат";
            return answer;
        }
        Entry entry = entryData.getEntryById(entryId);
        DateFormat format = new SimpleDateFormat("HH:mm");
        Date time;
        Date currentDate = new Date();
        try {
            time = format.parse(entry.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            answer = "Что-то пошло не так, обратитесь а администратору";
            return answer;
        }
        if (entry.getDate().after(currentDate) && time.after(currentDate)) {
            entry.setStatus("close");
            answer = "Запись c ID: "+textMsg+" закрыта";
        } else {
            answer = "Вы можете закрыть запись только после ее начала";
        }
        return answer;
    }
}
