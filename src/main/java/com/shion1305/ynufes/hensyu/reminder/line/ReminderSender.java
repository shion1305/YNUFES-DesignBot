package com.shion1305.ynufes.hensyu.reminder.line;

import com.linecorp.bot.model.PushMessage;
import com.shion1305.ynufes.hensyu.ConfigManager;
import com.shion1305.ynufes.hensyu.line.LineClientManger;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class ReminderSender {
    private static Timer timer = new Timer();

    public static void schedule(String title, String deadline, Date date) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ConfigManager.refresh();
                try {
                    LineClientManger.getClient().pushMessage(new PushMessage(ConfigManager.getConfig("Target"), ReminderMessageGenerator.createMessage(title, deadline))).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, date);
    }

    public static void resetAll() {
        //cancel tasks in timer
        timer.cancel();
        //remove all canceled task from timer's task queue
        timer.purge();
        timer = new Timer();
    }
}
