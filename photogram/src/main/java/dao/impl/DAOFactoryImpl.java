package dao.impl;

import dao.*;
import dao.factory.DAOFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sergey on 13.11.2016.
 */
public class DAOFactoryImpl extends DAOFactory {

    public Connection createConnection() {

        Context ctx = null;
        try {
            ctx = new InitialContext();
            return ((DataSource) ctx.lookup("jdbc:postgresql://localhost/photogram")).getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

        return null;
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
