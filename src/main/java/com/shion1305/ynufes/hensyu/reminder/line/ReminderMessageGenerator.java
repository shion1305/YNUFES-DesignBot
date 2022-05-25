package com.shion1305.ynufes.hensyu.reminder.line;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ReminderMessageGenerator {


    public static Message createMessage(String remindTitle, String remindDeadline) {
        return createMessage(remindTitle, remindDeadline, null, null);
    }

    public static Message createMessage(String remindTitle, String remindDeadline, String buttonText, String buttonLink) {
        Box hero = Box.builder()
                .layout(FlexLayout.VERTICAL)
                .content(Text.builder()
                        .text("Reminder")
                        .align(FlexAlign.CENTER)
                        .color("#ffffff")
                        .size(FlexFontSize.SM)
                        .build())
                .backgroundColor("#eb13e7").build();
        List<FlexComponent> bodyComponents = new ArrayList<>();
        bodyComponents.add(Text.builder().align(FlexAlign.CENTER).text(remindTitle).size(FlexFontSize.XXL).wrap(true).build());
        if (buttonText != null && buttonLink != null) {
            URI uri = URI.create(buttonLink);
            bodyComponents.add(Button.builder().action(new URIAction(buttonText, uri, new URIAction.AltUri(uri))).style(Button.ButtonStyle.SECONDARY).build());
        }
        Box body = Box.builder().layout(FlexLayout.VERTICAL)
                .contents(bodyComponents).build();
        Box footer = Box.builder()
                .layout(FlexLayout.VERTICAL)
                .content(Text.builder().align(FlexAlign.END).text(remindDeadline).size(FlexFontSize.XS).build()).build();
        Bubble bubble = Bubble.builder()
                .size(Bubble.BubbleSize.KILO)
                .hero(hero)
                .body(body)
                .footer(remindDeadline == null ? null : footer)
                .build();
        return FlexMessage.builder()
                .altText("ï“èWïîReminder")
                .contents(bubble)
                .build();
    }
}
