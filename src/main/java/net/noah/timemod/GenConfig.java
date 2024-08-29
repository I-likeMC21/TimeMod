package net.noah.timemod;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "timemod")
@Config(name = "my-config", wrapperName = "MyConfig")
public class GenConfig {
    public boolean shadow = false;
    public String hex = "FFFFFF";
    public String format = "yy dd hh:ss";
}