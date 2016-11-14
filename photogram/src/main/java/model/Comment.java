package model;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Comment {

    private String text;
    private User author;

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {

        return text;
    }

    public User getAuthor() {
        return author;
    }
}
