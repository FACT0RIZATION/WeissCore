package weissmoon.core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import weissmoon.core.lib.ReferenceCore;

public class PacketHandler {
     public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ReferenceCore.MOD_ID.toLowerCase());
    public static void init(){
        INSTANCE.registerMessage(SoundPlayMessage.class, SoundPlayMessage.class, 0, Side.SERVER);
    }

}
