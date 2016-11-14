package dao.factory;

import dao.*;
import dao.impl.DAOFactoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Фабрика объектов для работы с базой данных
 */
public abstract class DAOFactory {

    public static final int POSTGRESQL = 1;

    public static DAOFactoryImpl getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case POSTGRESQL:
                return new DAOFactoryImpl();
            default           :
                return null;
        }
    }
    /**
     * Возвращает подключение к базе данных
     */
    public abstract Connection getConnection() throws SQLException;

    public abstract CommentDAOInterface getCommentDAOInterface();

    public abstract LikeDAOInterface getLikeDAOInterface();

    public abstract PhotoDAOInterface getPhotoDAOInterface();

    public abstract PostDAOInterface getPostDAOInterface();

    public abstract UserDAOInterface getUserDAOInterface();

    public abstract TokenDAOInterface getTokenDAOInterface();

}

