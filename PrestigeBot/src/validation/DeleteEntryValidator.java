package validation;

import data.EntryData;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 21.08.2017.
 */
public class DeleteEntryValidator {

    private EntryData entryData = new EntryData();

    private int validate(String textMsg) {
        String array[] =  {
                "(^[1-9](\\d+)?\\s*?$)|(^([1-9](\\d+)?\\s*?,)+[1-9](\\d+)?\\s*?)$",
                "\\d{2}\\.\\d{2}\\.\\d{2}",
                "[d,D]elete all",
                "\\S*\\s\\S*"
        };

        int i = 0;
        Pattern p;
        Matcher m;

        for (String el: array) {
            i++;
            p = Pattern.compile(el);
            m = p.matcher(textMsg);
            if (m.matches()) {
                return i;
            }
        }

        return 0;
    }

    public String deleteEntryFromMsg(String textMsg) {
        int typeOfQuery = validate(textMsg);
        StringBuilder err = new StringBuilder();

        switch (typeOfQuery) {
            case 1:
                String[] arrayIds = textMsg.split(",");
                for (String el: arrayIds) {
                    if (entryData.deleteEntryById(Integer.parseInt(el))) {
                        err.append("Операция удаления записи с ID ").append(el).append(" - выполнена");
                    } else {
                        err.append("Записи с ID ").append(el).append(" не найдено");
                    }

                }
                break;
            case 2:
                String[] dayAndMounth = textMsg.split("\\.",3);
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayAndMounth[0].trim()));
                calendar.set(Calendar.MONTH, Integer.parseInt(dayAndMounth[1].trim()));
                calendar.set(Calendar.YEAR, Integer.parseInt(dayAndMounth[2].trim()));
                entryData.DeleteEntryByDate(calendar);
                err.append("Операция удаления по дате - выполнена");
                break;
            case 4:
                entryData.DeleteEntryByName(textMsg);
                err.append("Операция удаление по имени выполнена");
                break;
            case 3:
                entryData.deleteAll();
                err.append("Операция удаления всех записей выполнена");
                break;
            default:
                err.append("Не верный формат");
                break;
        }

        return err.toString();
    }
}
