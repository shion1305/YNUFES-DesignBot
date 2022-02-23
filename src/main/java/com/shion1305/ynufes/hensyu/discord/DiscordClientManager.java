package com.shion1305.ynufes.hensyu.discord;

import com.shion1305.ynufes.hensyu.ConfigManager;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.gateway.intent.IntentSet;

public class DiscordClientManager {
    private static final GatewayDiscordClient client = DiscordClient.create(ConfigManager.getConfig("DiscordToken")).gateway().setEnabledIntents(IntentSet.all()).login().block();

    public static GatewayDiscordClient getClient() {
        return client;
    }
}
