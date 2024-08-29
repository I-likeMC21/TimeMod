package net.noah.timemod;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class TimemodCommands implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
        dispatcher.register(ClientCommandManager.literal("timemod-shadow_enabled").
        then(argument("enabled", BoolArgumentType.bool()).
        executes(context -> {
            final boolean value = BoolArgumentType.getBool(context, "enabled");
            context.getSource().sendFeedback(Text.literal("Text shadow set to " + value));
            TimeMod.CONFIG.shadow(value);
            return 1;
        }))));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
        dispatcher.register(ClientCommandManager.literal("timemod-text_hex_color").
        then(argument("hexCode", StringArgumentType.string()).
        executes(context -> {
            final String hexCode = StringArgumentType.getString(context, "hexCode");
            if (!hexCode.matches("^#?[0-9A-Fa-f]{6}$")) {
                context.getSource().sendFeedback(Text.literal("Invalid hex code. Please enter a valid 6-digit hex code (e.g., #FF0000)."));
                    return 0;
                }
            TimeMod.CONFIG.hex(hexCode);
            context.getSource().sendFeedback(Text.literal("Hex code set successfully: " + hexCode));
            return 1;
        }))));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("timemod-format").

                    then(argument("string", StringArgumentType.word()).
                            executes(context -> {
                                final String format = StringArgumentType.getString(context, "string");
                                String formattedTime = null;
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                                    formattedTime = LocalDateTime.now().format(formatter);
                                } catch (IllegalArgumentException e) {

                                    context.getSource().sendFeedback(Text.literal("Invalid format '" + format +"'. For full documentation, click §5Here").setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com"))));

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

