package model;

import java.io.File;

/**
 * Created by Сергей on 07.11.2016.
 */
public class Photo {

    private User author;
    private File file;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
