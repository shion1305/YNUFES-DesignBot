package com.shion1305.ynufes.hensyu.discord;

import com.shion1305.ynufes.hensyu.ConfigManager;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.gateway.intent.IntentSet;

import javax.servlet.ServletContextListener;

public class DiscordClientManager implements ServletContextListener {
    private static final GatewayDiscordClient client = DiscordClient.builder(ConfigManager.getConfig("DiscordToken")).build().gateway().setEnabledIntents(IntentSet.all()).login().block();

    public static GatewayDiscordClient getClient() {
        return client;
    }
}
