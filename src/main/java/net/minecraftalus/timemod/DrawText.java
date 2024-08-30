package net.minecraftalus.timemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static net.minecraftalus.timemod.KeyBindInitializer.stopStart;
import static net.minecraftalus.timemod.KeyBindInitializer.pauseResume;
public class DrawText implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Timer timer = new Timer();
        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            int padding = TimeMod.CONFIG.padding();

            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                while (stopStart.wasPressed()) {
                    if(timer.isRunning) {
                        client.player.sendMessage(Text.literal("Timer stopped!"), true);
                        timer.TimerStop();
                    } else {
                        client.player.sendMessage(Text.literal("Timer started!"), true);
                        timer.TimerStart();
                    }
                }
            });

            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                while (pauseResume.wasPressed()) {
                    if(timer.isPaused) {
                        client.player.sendMessage(Text.literal("Timer resumed!"), true);
                        timer.TimerResume();
                    } else {
                        client.player.sendMessage(Text.literal("Timer paused!"), true);
                        timer.TimerPause();
                    }
                }
            });

            boolean shadowEnabled = TimeMod.CONFIG.shadow();
            String hex = TimeMod.CONFIG.hex();

            String format = TimeMod.CONFIG.format();
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            String formattedTime = currentTime.format(formatter);
            int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(formattedTime) * 2;
            int screenWidth = MinecraftClient.getInstance().getWindow().getWidth();
            int x = screenWidth - textWidth - 2;
            x-=(x/2);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, formattedTime, x-padding, padding*2, Integer.parseInt(hex, 16), shadowEnabled);

            if(timer.isRunning || timer.isPaused) {
                textWidth = MinecraftClient.getInstance().textRenderer.getWidth(timer.getTime()) * 2;
                screenWidth = MinecraftClient.getInstance().getWindow().getWidth();
                x = screenWidth - textWidth - 2;
                x-=(x/2);
                drawContext.drawText(MinecraftClient.getInstance().textRenderer, timer.getTime(), x-padding, 10+(padding*2), Integer.parseInt(hex, 16), shadowEnabled);
            }
        }));
    }
}