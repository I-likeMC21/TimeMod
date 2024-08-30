package net.minecraftalus.timemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeyBindInitializer implements ClientModInitializer {
    public static KeyBinding stopStart;
    public static KeyBinding pauseResume;
    @Override
    public void onInitializeClient() {
        stopStart = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fabric-key-binding-api-v1-testmod.stopstart", GLFW.GLFW_KEY_I, "key.category.first.test"));
        pauseResume = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fabric-key-binding-api-v1-testmod.pauseresume", GLFW.GLFW_KEY_O, "key.category.first.test"));
    }

}
