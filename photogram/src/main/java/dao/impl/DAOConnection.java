package dao.impl;

import dao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sergey on 13.11.2016.
 */
public class DAOConnection {

    Connection getConnection()
    {
        try {
            return DAOFactory.getDAOFactory(1).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
