/**
 * Created by Сергей on 05.08.2017.
 */

import entity.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class PrestigeBot extends TelegramLongPollingBot {

    private Data dataBase = new Data();


    {
        //User sergey = new User(273255483, "Sergey", "Kuznetsov", Role.OWNER);
        //User sergey2 = new User(273255483, "Sergey", "Kuznetsov", Role.ADMIN);

        //dataBase.addNewUser(sergey);
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
       } //  else if (update.hasCallbackQuery()) {
//            // Set variables
//
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//
//            if (call_data.equals("update_msg_text")) {
//                sendMsg(update.getCallbackQuery().getMessage(),"123");
//            }
//        }
    }

    private void registrate(Message message) {
        sendMsg(message, "Пожалуйста, представтесь");

//        SendMessage sendMessage = new SendMessage() // Create a message object object
//                .setChatId(message.getChatId())
//                .setText("some text");
//        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
//        // Set the keyboard to the markup
//        rowsInline.add(rowInline);
//        // Add it to the message
//        markupInline.setKeyboard(rowsInline);
//        sendMessage.setReplyMarkup(markupInline);
//        try {
//            sendMessage(sendMessage); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }



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