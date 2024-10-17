package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.javarush.telegrambot.SettingsBot;

import java.util.Map;

import static com.javarush.telegrambot.SettingsBot.BOT_NAME;
import static com.javarush.telegrambot.SettingsBot.BOT_TOKEN;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = BOT_NAME;
    public static final String TOKEN = BOT_TOKEN;

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // обработчики команд бота и введенного текста
        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Привет!");
        }

        if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("Пока!");
        }

        if (getMessageText().contains("бомба")) {
            sendTextMessageAsync("Опасность!");
        }

        if (getMessageText().contains("картинка")) {
            sendPhotoMessageAsync("step_8_pic");
        }

        if (getMessageText().contains("кот")) {
            sendTextMessageAsync("Выбери номер кота: ",
                    Map.of("кот 1", "cat1", "кот 2", "cat2"));
        }


        // Обработчики нажатий кнопок

        if (getCallbackQueryButtonKey().equals("cat1")) {
            sendPhotoMessageAsync("step_1_pic");
        }

        if (getCallbackQueryButtonKey().equals("cat2")) {
            sendPhotoMessageAsync("step_2_pic");
        }


    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}