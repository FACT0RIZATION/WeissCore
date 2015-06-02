package weissmoon.core;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import java.io.File;

import weissmoon.core.enchantment.Resiliance;
import weissmoon.core.event.EntityDrop;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.item.WeissDummy;
import weissmoon.core.lib.Strings;
import weissmoon.core.proxy.CommonProxy;
import weissmoon.core.command.TogglePVP;
import weissmoon.core.lib.ReferenceCore;

@Mod (modid = ReferenceCore.MOD_ID, name = ReferenceCore.MOD_NAME, version = ReferenceCore.VERSION, guiFactory = ReferenceCore.GUI_FACTORY_CLASS)
public class WeissCore {
    
    @Instance(ReferenceCore.MOD_ID)
    public static WeissCore instance;

    @SidedProxy(clientSide = "weissmoon.core.proxy.ClientProxy", serverSide = "weissmoon.core.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static final Item dummyItem = new WeissDummy();
    private boolean dummyInitialized = false;

    public static class Enchantments {
        public static Enchantment explosive;
    }

    public void initDummyItem(){
        if (!instance.dummyInitialized){
            instance.dummyInitialized = true;
            GameRegistry.registerItem(dummyItem, Strings.DUMMY_ITEM_STRING);
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + ReferenceCore.AUTHOR + File.separator + ReferenceCore.MOD_ID + ".cfg"));
    }
   
    @EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new EntityDrop());
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
