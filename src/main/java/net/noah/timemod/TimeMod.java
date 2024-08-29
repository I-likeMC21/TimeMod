package net.noah.timemod;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeMod implements ModInitializer {
	public static final String MOD_ID = "timemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.noah.timemod.MyConfig CONFIG = net.noah.timemod.MyConfig.createAndLoad();
	public static boolean value;
	@Override

	public void onInitialize() {
		new GenConfig();
		new DrawText().onInitializeClient();
		new TimemodCommands().onInitializeClient();

		LOGGER.info("Time Mod Initialized");
	}
}