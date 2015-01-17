package weissmoon.core;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import weissmoon.core.proxy.CommonProxy;
import weissmoon.core.command.TogglePVP;
import weissmoon.core.lib.ReferenceCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;


@Mod (modid = ReferenceCore.MOD_ID, name = ReferenceCore.MOD_NAME, version = ReferenceCore.VERSION, dependencies = "required-after:Forge@[10.12.2.1121,)")
public class WeissCore {
    
    @Instance(ReferenceCore.MOD_ID)
    public static WeissCore instance;

    @SidedProxy(clientSide = "weissmoon.core.proxy.ClientProxy", serverSide = "weissmoon.core.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPostInitializationEvent event){

    }
   
    @EventHandler
    public void init(FMLPostInitializationEvent event){
        proxy.registerEventHandler();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){}

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
        evt.registerServerCommand(new TogglePVP(evt.getServer()));
    }
}
