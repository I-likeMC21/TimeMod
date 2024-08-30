package net.minecraftalus.timemod;
import net.fabricmc.api.ModInitializer;
import net.minecraftalus.timemod.commands.TimeModCommandInitializer;
import net.minecraftalus.timemod.config.GenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeMod implements ModInitializer {
	public static final String MOD_ID = "timemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.minecraftalus.timemod.config.MyConfig CONFIG = net.minecraftalus.timemod.config.MyConfig.createAndLoad();

	@Override
	public void onInitialize() {
		new GenConfig();
		new DrawText().onInitializeClient();
		new TimeModCommandInitializer().onInitializeClient();
		new KeyBindInitializer().onInitializeClient();
		LOGGER.info("Time Mod Initialized");
	}
}