package net.minecraftalus.timemod.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.*;
import net.minecraftalus.timemod.TimeMod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class SetFormat implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("timemod-format").
                    then(argument("string", StringArgumentType.word()).
                            executes(context -> {
                                final String format = StringArgumentType.getString(context, "string");
                                String formattedTime = null;
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                                    formattedTime = LocalDateTime.now().format(formatter);
                                } catch (Exception e) {

                                    //context.getSource().sendFeedback(Text.literal("Invalid format '" + format +"'. For full documentation, click §3§l§nhere").setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("TEXT"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/I-likeMC21/TimeMod"))));

                                    MutableText hereText = Text.literal("§3§l§nhere");
                                    hereText.setStyle(Style.EMPTY
                                            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("TEXT")))
                                            .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/I-likeMC21/TimeMod")));

                                    MutableText fullText = Text.literal("Invalid format '").append(format).append("'. For full documentation, click ");
                                    context.getSource().sendFeedback(fullText.append(hereText));

                                    return 0;
                                }
                                final String formatFinal = format.replaceAll("_"," ").replaceAll("\\.",":");
                                TimeMod.CONFIG.format(formatFinal);

                                context.getSource().sendFeedback(Text.literal("Format set succesfully: " + format));
                                return 1;
                            })));
        });
    }
}
