package dao.impl;

import dao.*;
import dao.factory.DAOFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sergey on 13.11.2016.
 */
public class DAOFactoryImpl extends DAOFactory {

    public DAOFactoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private final String connectionUrl = "jdbc:postgresql://localhost:5432/photogram";
    private final String dbUser = "postgres";
    private final String dbPwd = "123";

    public Connection createConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }


    public Connection getConnection() throws SQLException {
        return createConnection();
    }

    public CommentDAOInterface getCommentDAOInterface() {
        return new CommentDAO();
    }

    public LikeDAOInterface getLikeDAOInterface() {
        return new LikeDAO();
    }

    public PhotoDAOInterface getPhotoDAOInterface() {
        return new PhotoDAO();
    }

    public PostDAOInterface getPostDAOInterface() {
        return new PostDAO();
    }

    public UserDAOInterface getUserDAOInterface() {
        return new UserDAO();
    }

    public TokenDAOInterface getTokenDAOInterface() {
        return new TokenDAO();
    }
}
