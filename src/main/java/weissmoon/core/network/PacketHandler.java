package weissmoon.core.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import weissmoon.core.lib.ReferenceCore;

public class PacketHandler{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ReferenceCore.MOD_ID);

    public static void init (){
        INSTANCE.registerMessage(KeyPressedActionMessage.class, KeyPressedActionMessage.class, 0, Side.SERVER);
        INSTANCE.registerMessage(SpawnBlockParticlesPacket.class, SpawnBlockParticlesPacket.class, 1, Side.CLIENT);
//        INSTANCE.registerMessage(BoundSoundPacket.class, BoundSoundPacket.class, 0, Side.SERVER);
        //INSTANCE.registerMessage(OpenHandPacket.class, OpenHandPacket.class, 0, Side.SERVER);
        //INSTANCE.registerMessage();
    }
}
