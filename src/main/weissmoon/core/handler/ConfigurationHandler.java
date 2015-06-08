package weissmoon.core.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import weissmoon.core.lib.ReferenceCore;

public class ConfigurationHandler {
    public static Configuration configuration;

    public static boolean lore;
    public static int resiliance;
    public static int dealFire;

    public static void init(File configFile) {

        if (configuration == null){
            configuration = new Configuration(configFile);
            loadCofiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(ReferenceCore.MOD_ID)){
            loadCofiguration();
        }
    }

    private static void loadCofiguration(){

        lore = configuration.getBoolean("Lore", Configuration.CATEGORY_GENERAL, true, "Global Lore setting");
        resiliance = configuration.getInt("Resilinace", Configuration.CATEGORY_GENERAL, 89, 0, 255, "Resialiance Enchantment ID.");

        if (configuration.hasChanged()){
            configuration.save();
        }
    }

}