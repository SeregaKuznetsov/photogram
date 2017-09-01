package security;

import java.util.Date;

/**
 * Created by Sergey on 01.09.2017.
 */
public class Key {
    public final int liveTime = 600;
    private String key;
    private Date finishTime;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    Key() {
        active = true;
    }

    public String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    void setFinishTime(Date creationTime) {
        this.finishTime = creationTime;
    }


}
