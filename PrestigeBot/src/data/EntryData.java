package data;

import entity.Entry;

import java.util.*;

/**
 * Created by Sergey on 16.08.2017.
 */
public class EntryData {
    private List<Entry> entries = new ArrayList<>();

    public void add(Entry entry) {
        entries.add(entry);
    }

    public void delete(Entry entry) {
        entries.remove(entry);
    }

    public List<Entry> getEntryByDate(Calendar calendar) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getDate().equals(calendar))
                result.add(entry);
        }
        return result;
    }

    public boolean find(Entry entry) {
        return entries.contains(entry);
    }
}
