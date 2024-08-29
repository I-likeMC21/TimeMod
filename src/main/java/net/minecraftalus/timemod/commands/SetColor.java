package net.minecraftalus.timemod.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraftalus.timemod.TimeMod;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class SetColor implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
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
    }
}
