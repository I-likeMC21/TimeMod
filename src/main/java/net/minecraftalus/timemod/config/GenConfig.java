package net.minecraftalus.timemod.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

//@Modmenu(modId = "timemod")
@Config(name = "timemodconfig", wrapperName = "MyConfig")
public class GenConfig {
    public boolean shadow = false;
    public String hex = "FFFFFF";
    public String format = "dd hh:mm:ss";
    public int padding = 2;
}