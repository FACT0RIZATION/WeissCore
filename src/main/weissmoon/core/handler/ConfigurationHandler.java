package weissmoon.core.handler;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import weissmoon.core.lib.ReferenceCore;

import java.io.File;


public class ConfigurationHandler{
    public static Configuration configuration;

    public static boolean lore;
    public static boolean materialEvent;
    public static boolean michaelis;
    public static boolean wolfTip;
    public static boolean extraFancy;


    public static String CATEGORY_GENERAL = "general";
    public static String CATEGORY_TOOLTIP = "tooltip";
    public static String CATEGORY_RENDER = "render";

    public static void init (File configFile){
        if (configuration == null){
            configuration = new Configuration(configFile);
            loadCofiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.getModID().equalsIgnoreCase(ReferenceCore.MOD_ID)){
            loadCofiguration();
        }
    }

    private static void loadCofiguration (){
        lore = configuration.getBoolean("lore", CATEGORY_GENERAL, true, "Global Lore setting");
        michaelis = configuration.getBoolean("michaelis", CATEGORY_GENERAL, false, "Certain bullets appear in a shot player's inventory if PVP is disabled" + configuration.NEW_LINE + "Otherwise bullets phase through");

        extraFancy = configuration.getBoolean("extraFancy", CATEGORY_RENDER, true, "Render some items with extra fancy render");

        materialEvent = configuration.getBoolean("blockMaterial", CATEGORY_TOOLTIP, true, "Show block material when holding \"Shift\"");
        wolfTip = configuration.getBoolean("wolfTip", CATEGORY_TOOLTIP, true, "Show wolves favorite foods");


        if (configuration.hasChanged()){
            configuration.save();
        }
    }
}