package net.minecraftalus.timemod.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraftalus.timemod.TimeMod;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class SetShadow implements ClientModInitializer {
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
    }
}
