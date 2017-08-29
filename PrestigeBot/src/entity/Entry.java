package entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 06.08.2017.
 */
public class Entry {

    private long id;
    private Calendar date;
    private String time;
    private int count;
    private String cost;
    private String notes;
    private String madeBy;
    private Date creationTime;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    private SimpleDateFormat format1 = new SimpleDateFormat("dd MMMMM yy EEEEEEEEEEEE");
    private SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yy");

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String entryToString(Entry entry) {
        return "Дата: " + format1.format(entry.getDate().getTime()) +
                "\nВремя: " + entry.getTime() +
                "\nЧисло человек: " + entry.getCount() +
                "\nСтоимость: " + entry.getCost() +
                "\nПримечание: " + entry.getNotes() +
                "\nСделана: " + entry.getMadeBy() +
                "\nКогда была добавлена " + format2.format(entry.getCreationTime()) +
                "\nID: " + entry.getId();
    }
}
