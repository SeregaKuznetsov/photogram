package dao.impl;

import dao.UserDAOInterface;
import model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 13.11.2016.
 */
public class UserDAO extends DAOConnection implements UserDAOInterface {


    public void addUser(User user) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO user "
                    + "(name, password, email, age)"
                    + "VALUES( ?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getAge());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void deleteUser(User user) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM user WHERE id =  ?");
            stmt.setInt(1, user.getId());

            stmt.execute();
            //log.trace("Addition to notes by user " + note.getUser().getUsername());
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //log.error("Addition of new comment failed " + e.getLocalizedMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        User s = new User();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setAge(rs.getInt("age"));
            } else {
                s = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User s = new User();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setAge(rs.getInt("age"));
            } else {
                s = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

    public void update(User user) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("UPDATE user SET name =  ?, password=?, email=?, age = ?, " +
                    "WHERE id =  ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getAge());

            stmt.execute();
            //log.trace("Addition to notes by user " + note.getUser().getUsername());
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //log.error("Addition of new comment failed " + e.getLocalizedMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        List<User> list = new ArrayList<User>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            User s= new User();
            while (rs.next()) {

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setAge(rs.getInt("age"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
