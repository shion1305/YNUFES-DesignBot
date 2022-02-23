package com.shion1305.ynufes.hensyu.reminder.line;

import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.component.box.BoxLinearGradient;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;

public class ReminderMessageGenerator {
    public static Message createMessage(String remindTitle, String remindDeadline) {
        Box hero = Box.builder()
                .layout(FlexLayout.VERTICAL)
                .content(Text.builder()
                        .text("Reminder")
                        .align(FlexAlign.CENTER)
                        .color("#ffffff")
                        .size(FlexFontSize.SM)
                        .build())
                .background(BoxLinearGradient.builder().angle("110deg").startColor("#ad13eb").endColor("#eb13e7")
                        .build()).build();
        Box body = Box.builder().layout(FlexLayout.VERTICAL)
                .content(Text.builder().align(FlexAlign.CENTER).text(remindTitle).size(FlexFontSize.XXL).wrap(true).build()).build();

        Box footer = Box.builder()
                .layout(FlexLayout.VERTICAL)
                .content(Text.builder().align(FlexAlign.END).text(remindDeadline).size(FlexFontSize.XS).build()).build();
        Bubble bubble = Bubble.builder()
                .size(Bubble.BubbleSize.KILO)
                .hero(hero)
                .body(body)
                .footer(footer)
                .build();
        return FlexMessage.builder()
                .altText("編集部Reminder")
                .contents(bubble)
                .build();
    }
}
