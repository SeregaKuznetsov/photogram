package entity;

/**
 * Created by Сергей on 06.08.2017.
 */
public class User {
    private int id;
    private String name;
    private String lasName;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String name, String lasName, Role role) {
        this.id = id;
        this.name = name;
        this.lasName = lasName;
        this.role = role;
    }

    public User(int id, String name, String lasName) {
        this.id = id;
        this.name = name;
        this.lasName = lasName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }
}
