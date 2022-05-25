package com.shion1305.ynufes.hensyu.reminder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebListener
public class ReminderHost implements ServletContextListener {
    private static final Logger logger = Logger.getLogger("ReminderHost");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        init();
    }

    public static void init() {
        try (Scanner s = new Scanner(new FileInputStream(System.getProperty("user.home") + "/ShionServerConfig/YNUFES-Design/Reminders"), StandardCharsets.UTF_8)) {
            while (s.hasNextLine()) {
                String s1 = s.nextLine();
                Pattern pattern = Pattern.compile("([^,]+),([^,]*),([^,]*),([^,]*),([^,]*)");
                Matcher matcher = pattern.matcher(s1);
                if (!matcher.find()) continue;
                String title = matcher.group(1);
                String deadline = matcher.group(2);
                Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(matcher.group(3));
                ReminderSender.schedule(title, deadline, date, matcher.group(4), matcher.group(5));
                logger.info("Scheduled " + title + " | " + deadline + " | " + date.toString());
            }
        } catch (ParseException | FileNotFoundException e) {
            logger.severe("SERIOUS ERROR");
            e.printStackTrace();
        }
    }
}