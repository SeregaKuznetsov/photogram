package dao.impl;

import dao.TokenDAOInterface;
import model.Token;
import model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Sergey on 13.11.2016.
 */
public class TokenDAO extends DAOConnection implements TokenDAOInterface{

    public void create(Token token) {
        PreparedStatement stmt = null;
        Connection con = getConnection();

        try {
            stmt = con.prepareStatement("UPDATE users SET uuid =  ?, deletedate=? " +
                    "WHERE id =  ?");
            stmt.setString(1, token.getUuid());
            stmt.setDate(2, new java.sql.Date(token.getDeleteDate().getTime()));
            stmt.setInt(3, token.getUser().getId());
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


    public Token readToken(String uuid) {
        String sql = "SELECT * FROM users WHERE uuid = ?";
        User s = null;
        Token t=new Token();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, uuid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setAge(rs.getInt("age"));
                t.setUuid(uuid);
                t.setDeleteDate(rs.getDate("deletedate"));
                t.setUser(s);
            }
            else {t=null;}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    public void deleteUser(Token token) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM users WHERE uuid =  ?");
            stmt.setString(1, token.getUuid());

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

    public void deleteToken(Token token) {
        PreparedStatement stmt = null;
        Connection con = getConnection();

        try {
            stmt = con.prepareStatement("UPDATE users SET uuid =  NULL, deletedate=NULL " +
                    "WHERE uuid =  ?");
            stmt.setString(1, token.getUuid());
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

    public void deleteToken(Date date) {
        PreparedStatement stmt = null;
        Connection con = getConnection();

        try {
            stmt = con.prepareStatement("DELETE FROM users  " +
                    "WHERE deletedate<?");
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            stmt.execute();
            //log.trace("Addition to notes by user " + note.getUser().getUsername());
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

    public User readUser(String uuid) {
        String sql = "SELECT * FROM users WHERE uuid = ?";
        User s = null;
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, uuid);
            ResultSet rs = stm.executeQuery();
            rs.next();

            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setPassword(rs.getString("password"));
            s.setEmail(rs.getString("email"));
            s.setAge(rs.getInt("age"));

        } catch (SQLException e) {
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
}
