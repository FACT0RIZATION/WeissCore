package weissmoon.core;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import weissmoon.core.enchantment.Resiliance;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.proxy.CommonProxy;
import weissmoon.core.command.TogglePVP;
import weissmoon.core.lib.ReferenceCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import java.io.File;


@Mod (modid = ReferenceCore.MOD_ID, name = ReferenceCore.MOD_NAME, version = ReferenceCore.VERSION, dependencies = "required-after:Forge@[10.12.2.1121,)", guiFactory = ReferenceCore.GUI_FACTORY_CLASS)
public class WeissCore {
    
    @Instance(ReferenceCore.MOD_ID)
    public static WeissCore instance;

    @SidedProxy(clientSide = "weissmoon.core.proxy.ClientProxy", serverSide = "weissmoon.core.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static class Enchantments {
        public static Enchantment explosive;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + ReferenceCore.AUTHOR + File.separator + ReferenceCore.MOD_ID + ".cfg"));
    }
   
    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.registerEventHandler();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        if (ConfigurationHandler.resiliance > 0) {
            //MinecraftForge.EVENT_BUS.register(new LastStandEnchantmentsHandler());
            Enchantments.explosive = new Resiliance(ConfigurationHandler.resiliance);
        }
        proxy.initRenderer();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void registerEnchantment(FMLPostInitializationEvent event){}

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
        evt.registerServerCommand(new TogglePVP(evt.getServer()));
    }

}
