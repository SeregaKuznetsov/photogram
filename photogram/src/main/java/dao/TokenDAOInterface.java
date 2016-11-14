package dao;

import model.Token;
import model.User;

import java.util.Date;

/**
 * Created by Sergey on 13.11.2016.
 */
public interface TokenDAOInterface {
    /**
     * Создает новую запись и соответствующий ей объект
     */
    public void create(Token token);

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public Token readToken(String uuid);

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void deleteUser(Token token);

    public void deleteToken(Token token);

    public void deleteToken(Date date);

    public User readUser(String uuid);
}
