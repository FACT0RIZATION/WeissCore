package weissmoon.core.client.sound;

import net.minecraft.client.audio.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.*;

/**
 * Plays a sound that follows an entity.
 * Similar to minecart sound.
 */
@SideOnly(Side.CLIENT)
public class BoundSoundCore extends PositionedSound implements ITickableSound{
    protected Entity entity;
    protected boolean isDone = false;

    public BoundSoundCore (ResourceLocation p_i45103_1_, SoundCategory category, Entity entity, float loudness, float pitch){
        super(p_i45103_1_, category);
        this.entity = entity;
        this.pitch = 1.0F;
        this.volume = loudness;
        this.pitch = pitch;
    }

    public BoundSoundCore (ResourceLocation p_i45103_1_, SoundCategory category){
        super(p_i45103_1_, category);
    }


    @Override
    public float getXPosF(){
        return (float)this.entity.posX;
    }

    @Override
    public float getYPosF(){
        return (float)this.entity.posY;
    }

    @Override
    public float getZPosF(){
        return (float)this.entity.posZ;
    }


    @Override
    public boolean isDonePlaying (){
        return this.isDone;
    }

    @Override
    public void update (){
    }
}
