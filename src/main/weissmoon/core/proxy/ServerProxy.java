package weissmoon.core.proxy;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import weissmoon.core.network.PacketHandler;
import weissmoon.core.network.SoundPlayMessage;
import weissmoon.core.client.sound.BoundSoundCore;

public class ServerProxy extends CommonProxy {

        /*
            Send Packet to clients to play a BoundSound.
            Currently Broken.play a BoundSound.
            Currently Broken.
         */
    @Override
    public void playBoundSound(BoundSoundCore sound, Entity entity) {
        PacketHandler.INSTANCE.sendToAllAround(new SoundPlayMessage((EntityPlayer)entity), new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 10));
    }

    @Override
    public void registerEventHandler() {}

    public void initRenderer(){}

    @Override
    public void nanoSounds(int ID){

    }
}
