package net.minecraftalus.timemod;
import net.fabricmc.api.ModInitializer;
import net.minecraftalus.timemod.commands.TimeModCommandInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeMod implements ModInitializer {
	public static final String MOD_ID = "timemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.minecraftalus.timemod.MyConfig CONFIG = net.minecraftalus.timemod.MyConfig.createAndLoad();
	public static boolean value;
	@Override

	public void onInitialize() {
		new GenConfig();
		new DrawText().onInitializeClient();
		new TimeModCommandInitializer().onInitializeClient();

		LOGGER.info("Time Mod Initialized");
	}
}