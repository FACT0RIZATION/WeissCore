package weissmoon.core.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.lib.ReferenceCore;

public class ModGuiConfig extends GuiConfig{
    public ModGuiConfig (GuiScreen guiScreen){
        super(guiScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory("general")).getChildElements(), ReferenceCore.MOD_ID.toLowerCase(), false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}
