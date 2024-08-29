package net.minecraftalus.timemod.commands;

import net.fabricmc.api.ClientModInitializer;

public class TimeModCommandInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        new SetShadow().onInitializeClient();
        new SetColor().onInitializeClient();
        new SetFormat().onInitializeClient();

    }
}

