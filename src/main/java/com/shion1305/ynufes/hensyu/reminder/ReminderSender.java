package com.shion1305.ynufes.hensyu.reminder;

import com.linecorp.bot.model.PushMessage;
import com.shion1305.ynufes.hensyu.ConfigManager;
import com.shion1305.ynufes.hensyu.discord.DiscordClientManager;
import com.shion1305.ynufes.hensyu.line.LineClientManger;
import com.shion1305.ynufes.hensyu.reminder.line.ReminderMessageGenerator;
import discord4j.common.util.Snowflake;
import discord4j.core.spec.EmbedCreateFields;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

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
                    LineClientManger.getClient().pushMessage(new PushMessage(ConfigManager.getConfig("LineTarget"), ReminderMessageGenerator.createMessage(title, deadline))).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                DiscordClientManager.getClient().getChannelById(Snowflake.of(ConfigManager.getConfig("DiscordReminderChannel")))
                        .subscribe(channel -> channel.getRestChannel().createMessage(EmbedCreateSpec.builder()
                                .title(title)
                                .color(Color.RUBY)
                                        .author(EmbedCreateFields.Author.of("Reminder",null,null))
                                .description(deadline)
                                .build().asRequest()).block());
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
