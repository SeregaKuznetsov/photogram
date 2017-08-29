/**
 * Created by Сергей on 05.08.2017.
 */

import data.EntryData;
import data.MessageData;
import data.UserData;
import entity.*;
import entity.User;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import validation.DeleteEntryValidator;
import validation.NewEntryValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static entity.Role.*;

public class PrestigeBot extends TelegramLongPollingBot {

    private UserData userDataBase = new UserData();
    private MessageData messageData = new MessageData();
    private NewEntryValidator newEntryValidator = new NewEntryValidator();
    private DeleteEntryValidator deleteEntryValidator = new DeleteEntryValidator();
    private EntryData entryData = new EntryData();


    {
        //User sergey = new User(273255483, "Sergey", "Kuznetsov", Role.OWNER);
        //User sergey2 = new User(273255483, "Sergey", "Kuznetsov", Role.ADMIN);

        //userDataBase.addNewUser(sergey);
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
            String textMsg = message.getText();

            if (textMsg.equals("/help")) {
                sendMsg(message, "Привет, я робот");
            }

            if (waitingForMessage(userId)) {
                String action = messageData.getValue(userId);
                if (action.equals("Добавить запись")) {
                    Entry entry = newEntryValidator.getEntryFromMsg(textMsg, message.getFrom().getFirstName() +
                            " " + message.getFrom().getLastName());
                    entryData.add(entry);
                    // Удаление из очереди сообщений
                    messageData.delete(userId);
                    sendMsg(message, "Запись сохранена(Сделать валидацию)");
                    alertWorkers(entry);
                    sendMsg(message, "Работники уведомлены о новой записи");
                } else if (action.equals("Удалить запись")) {
                    String answer = deleteEntryValidator.deleteEntryFromMsg(textMsg);
                    sendMsg(message, answer);
                    messageData.delete(userId);
                }
            } else
            //Если пользователь зарегистрирован
            if (hasRegistered(userId)) {
                User currentUser = userDataBase.getUserById(userId);

                if (currentUser.getRole().equals(OWNER)) {
                    switch (textMsg) {
                        case "Добавить запись":
                            sendMsg(message, "Пожалуйста введите информацию в формате дд.мм, колличество человек, чч:мм," +
                            " стоимость, примечания(необязательно)");
                            sendMsg(message, "Например так: 02.03, 4, 15:45, 2000, нужна белая лошадь");
                            waitForMessage(userId, textMsg);
                            break;
                        case "Активные записи":
                            List<Entry> entries = entryData.getActiveEntries();
                            if (entries.isEmpty()) {
                                sendMsg(message, "Записи отсутствуют");
                            } else {
                                sendMsg(message, "Записи отсортированы по дате");
                                for (Entry entry : entries) {
                                    sendMsg(message, entry.entryToString(entry));
                                }
                            }
                            break;
                        case "Удалить запись":
                                sendMsg(message, "Вы можете удалить записи:" +
                                        "\nПо ID - например: 3 или 4,1,33" +
                                        "\nВсе записи по дате - например: 02.03.17" +
                                        "\nВсе записи по автору - например: Serega Kuznetsov" +
                                        "\nУдалить все записи - delete all");
                                waitForMessage(userId, textMsg);
                            break;
                        case "Документы":
                            sendMsg(message, "Сюда можно будет добавить скрины документов," +
                                    " чтобы под рукой были, ну или что нибудь еще");
                            break;
                        case "Закрытые записи":
                            List<Entry> closeEntries = entryData.getCloseEntries();
                            if (closeEntries.isEmpty()) {
                                sendMsg(message, "Записи отсутствуют");
                            } else {
                                sendMsg(message, "Записи отсортированы по дате");
                                for (Entry entry : closeEntries) {
                                    sendMsg(message, entry.entryToString(entry));
                                }
                            }
                            break;
                        case "О компании":
                            sendMsg(message, "Сюда можно вывести информацию о колличесве сотрудников, " +
                                    "загестрированных клинетах");
                            break;
                        case "Рассылка":
                            sendMsg(message, "Здесь будет доступна рассылка всем работникам/клиентам/админам");
                            break;
                        case "Добавить сотрудника":
                            sendMsg(message, "Здесь можно будет сгенерировать уникальный, одноразовый ключ для " +
                                    "добавления новых сотрудников");
                            break;
                        default:
                            sendMsg(message, "Привет, хозяин!");
                            showMenu(currentUser.getRole(), message);
                            break;
                    }

                } else if (currentUser.getRole().equals(ADMIN)) {
                    sendMsg(message, "Привет, админ!");
                } else if (currentUser.getRole().equals(WORKER)) {
                    sendMsg(message, "Привет, работник!");
                } else if (currentUser.getRole().equals(CLIENT)) {
                    sendMsg(message, "Привет, клиент!");
                } else if (currentUser.getRole().equals(ANONIMUS)) {
                    switch (textMsg) {
                        case "Владелец":
                            currentUser.setRole(OWNER);
                            sendMsg(message, "Вы зарегистрированы как владелец");
                            showMenu(currentUser.getRole(), message);
                            break;
                        case "Администратор":
                            currentUser.setRole(ADMIN);
                            sendMsg(message, "Вы зарегистрированы как администротор");
                            showMenu(currentUser.getRole(), message);
                            break;
                        case "Сотрудник":
                            currentUser.setRole(WORKER);
                            sendMsg(message, "Вы зарегистрированы как работник");
                            showMenu(currentUser.getRole(), message);
                            break;
                        case "Клиент":
                            currentUser.setRole(CLIENT);
                            sendMsg(message, "Вы зарегистрированы как клиент");
                            showMenu(currentUser.getRole(), message);
                            break;
                        default:
                            showRegistrateKeyboard(message);
                            break;
                    }
                }

            }
            //Если пользователь еще не зарегистрирован
            else {
                registrate(message);
            }
       }
    }

    private void alertWorkers(Entry entry) {
        List<User> workers = userDataBase.getAllWorkers();
        for (User worker: workers) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(worker.getChatid());
            sendMessage.setText(entry.entryToString(entry));
            sendMessage.enableMarkdown(true);
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean waitingForMessage(int from) {
        return messageData.find(from);
    }

    private void waitForMessage(int userId, String textMsg) {
        messageData.add(userId, textMsg);
    }

    private void showMenu(Role role, Message message) {
        switch (role) {
            case OWNER: {
                showOwnerMenu(message);
                break;
            }
            case ADMIN: {
                showAdminMenu(message);
                break;
            }
            case WORKER: {
                showWorkerMenu(message);
                break;
            }
            case CLIENT: {
                showClientMenu(message);
                break;
            }
            case ANONIMUS:
                showRegistrateKeyboard(message);
                break;
        }
    }

    private void showClientMenu(Message message) {

    }

    private void showWorkerMenu(Message message) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Что бы вы хотели сделать?");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row2.add("Посмотреть записи");
        row3.add("Закрыть запись");
        // Add the first row to the keyboard
        keyboard.add(row2);
        keyboard.add(row3);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setOneTimeKeyboard(true);

        // Add it to the message
        sendMessage.setReplyMarkup(keyboardMarkup);
        try {
            sendMessage(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void showAdminMenu(Message message) {
    }

    private void showOwnerMenu(Message message) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Что бы вы хотели сделать?");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        KeyboardRow row6 = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row1.add("Добавить запись");
        row2.add("Активные записи");
        row3.add("Закрытые записи");
        row4.add("Удалить запись");
        row5.add("Документы");
        row6.add("О компании");
        // Add the first row to the keyboard
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        keyboard.add(row6);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setOneTimeKeyboard(true);

        // Add it to the message
        sendMessage.setReplyMarkup(keyboardMarkup);
        try {
            sendMessage(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void showRegistrateKeyboard(Message message) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Пожалуйста, представтесь");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row1.add("Владелец");
        row2.add("Администратор");
        row3.add("Сотрудник");
        row4.add("Клиент");
        // Add the first row to the keyboard
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setOneTimeKeyboard(true);

        // Add it to the message
        sendMessage.setReplyMarkup(keyboardMarkup);
        try {
            sendMessage(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void registrate(Message message) {

        showRegistrateKeyboard(message);

        org.telegram.telegrambots.api.objects.User tellUser;
        tellUser = message.getFrom();
        int id = tellUser.getId();
        long chatId = message.getChatId();
        String name = tellUser.getFirstName();
        String lastName = tellUser.getLastName();
        User myUser = new User(id, name, lastName, ANONIMUS, chatId);
        userDataBase.addNewUser(myUser);

    }

    private boolean hasRegistered(Integer id) {

        List<User> users = userDataBase.getAllUsers();

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