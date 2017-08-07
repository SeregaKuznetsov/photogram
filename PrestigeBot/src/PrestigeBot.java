/**
 * Created by Сергей on 05.08.2017.
 */

import entity.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.List;

public class PrestigeBot extends TelegramLongPollingBot {

    private Data dataBase = new Data();


    {
        User sergey = new User(273255483, "Sergey", "Kuznetsov", Role.OWNER);
        //User sergey2 = new User(273255483, "Sergey", "Kuznetsov", Role.ADMIN);

        dataBase.addNewUser(sergey);
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new PrestigeBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Prestige_bot";
    }

    @Override
    public String getBotToken() {
        return "443470625:AAFLJnBVCdXDPE1k0oN-8mY2fmlicNZWBw8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            int userId = message.getFrom().getId();

            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, я робот");
            }

            //Если пользователь зарегистрирован
            if (hasRegistered(userId)) {
                User currentUser = dataBase.getUserById(userId);
                if (currentUser.getRole().equals(Role.OWNER)) {
                    sendMsg(message, "Привет, хозяин!");

                } else
                    sendMsg(message, "Привет");
            }
            //Если пользователь еще не зарегистрирован
            else {
                registrate(message);
                sendMsg(message, "Вы зарегестрированны");
            }
        }
    }

    private void registrate(Message message) {
        org.telegram.telegrambots.api.objects.User tellUser;
        tellUser = message.getFrom();
        int id = tellUser.getId();
        String name = tellUser.getFirstName();
        String lastName = tellUser.getLastName();
        User myUser = new User(id, name, lastName);
        dataBase.addNewUser(myUser);
    }

    private boolean hasRegistered(Integer id) {

        List<User> users = dataBase.getAllUsers();

        for (User user : users) {
            if (user.getId() == id)
                return true;
        }
        return false;
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}