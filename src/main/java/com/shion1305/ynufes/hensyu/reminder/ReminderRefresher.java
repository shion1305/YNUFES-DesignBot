package com.shion1305.ynufes.hensyu.reminder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/refreshReminder")
public class ReminderRefresher extends HttpServlet {
    private static final Logger logger = Logger.getLogger("RefreshReminder");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("REFRESH PROCESS STARTED");
        ReminderSender.resetAll();
        ReminderHost.init();
        resp.setStatus(200);
    }
}
