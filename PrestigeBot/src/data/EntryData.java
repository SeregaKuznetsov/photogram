package data;

import entity.Entry;

import java.util.*;

/**
 * Created by Sergey on 16.08.2017.
 */
public class EntryData {
    private List<Entry> entries = new ArrayList<>();
    private static long i = 1;

    public void add(Entry entry) {
        i++;
        entry.setId(i);
        entry.setCreationTime(new Date());
        entries.add(entry);
    }

    public void delete(Entry entry) {
        entries.remove(entry);
    }

    public void deleteEntryById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                entries.remove(entry);
                break;
            }
        }
    }

    public List<Entry> getEntryByDate(Calendar calendar) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getDate().get(Calendar.DAY_OF_YEAR) == (calendar.get(Calendar.DAY_OF_YEAR)))
                result.add(entry);
        }
        return result;
    }

    public boolean find(Entry entry) {
        return entries.contains(entry);
    }
}
