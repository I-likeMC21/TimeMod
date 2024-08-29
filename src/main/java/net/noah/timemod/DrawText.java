package net.noah.timemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DrawText implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {


            boolean shadowEnabled = TimeMod.CONFIG.shadow();

            String hex = TimeMod.CONFIG.hex();

            String format = TimeMod.CONFIG.format();

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            String formattedTime = currentTime.format(formatter);



            int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(formattedTime) * 2;
            int screenWidth = MinecraftClient.getInstance().getWindow().getWidth();
            int x = screenWidth - textWidth - 2; // Adjust the offset as needed
            x-=(x/2);

            drawContext.drawText(MinecraftClient.getInstance().textRenderer, formattedTime, x, 2, Integer.parseInt(hex, 16), shadowEnabled);

        }));
    }
}