package data;

import entity.Entry;

import java.util.*;

/**
 * Created by Sergey on 16.08.2017.
 */
public class EntryData {
    private static List<Entry> entries = new ArrayList<>();
    private static long i = 0;

    public void add(Entry entry) {
        i++;
        entry.setId(i);
        entry.setCreationTime(new Date());
        entries.add(entry);
    }

    public void delete(Entry entry) {
        entries.remove(entry);
    }

    public void deleteAll() {
        entries.removeAll(entries);
    }


    public boolean deleteEntryById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                entries.remove(entry);
                return true;
            }
        }
        return false;
    }

    public void DeleteEntryByDate(Calendar calendar) {
        List<Entry> listToRemove = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getDate().get(Calendar.DAY_OF_YEAR) == (calendar.get(Calendar.DAY_OF_YEAR)))
                listToRemove.add(entry);
        }

        entries.removeAll(listToRemove);
    }

    public void DeleteEntryByName(String name) {
        List<Entry> listToRemove = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getMadeBy().equals(name))
                listToRemove.add(entry);
        }
        entries.removeAll(listToRemove);
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

    private List<Entry> sortByDate(List<Entry> entries) {
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return entries;
    }

    public List<Entry> getActiveEntries() {
        List<Entry> listToRemove = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getStatus().equals("active"))
                listToRemove.add(entry);
        }
        return sortByDate(listToRemove);
    }

    public List<Entry> getCloseEntries() {
        List<Entry> listToRemove = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getStatus().equals("close"))
                listToRemove.add(entry);
        }
        return sortByDate(listToRemove);
    }

    public Entry getEntryById(int entryId) {
        return entries.get(entryId);
    }
}
