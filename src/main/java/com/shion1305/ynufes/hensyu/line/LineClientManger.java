package com.shion1305.ynufes.hensyu.line;

import com.linecorp.bot.client.LineMessagingClient;
import com.shion1305.ynufes.hensyu.ConfigManager;

public class LineClientManger {
    private static final LineMessagingClient client;

    static {
        client = LineMessagingClient.builder(ConfigManager.getConfig("LineBotToken")).build();
    }

    public static LineMessagingClient getClient() {
        return client;
    }
}
