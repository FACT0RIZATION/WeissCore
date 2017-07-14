package weissmoon.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraft.util.SoundCategory;
import weissmoon.core.network.*;

public class ServerProxy extends CommonProxy{
    /*
        Send Packet to clients to play a BoundSound.
        Currently Broken.play a BoundSound.
        Currently Broken.
     */
    public void playBoundSound (String sound, SoundCategory category, Entity entity, float loudness, float pitch){
        PacketHandler.INSTANCE.sendToAllAround(new BoundSoundPacket((EntityPlayer)entity, category, sound, loudness, pitch), new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 50.0D));
    }

    public void registerEventHandler (){
    }

    public void initRenderer (){
    }

    public void nanoSounds (int ID){
    }

    public void spawnBlockparticles (RayTraceResult movingObjectPosition, int dimID, Vec3d vec3d, boolean fancyOnly){
        PacketHandler.INSTANCE.sendToAllAround(new SpawnBlockParticlesPacket(), new NetworkRegistry.TargetPoint(dimID, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord,20.0D));
    }

    @Override
    public void registerKeyHandler(){
    }

    @Override
    public void registerRenderOverride() {

    }

}