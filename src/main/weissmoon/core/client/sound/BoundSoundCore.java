package weissmoon.core.client.sound;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


    /**
        Plays a sound that follows an entity.
        Similar to minecart sound.
     */
public abstract class BoundSoundCore extends PositionedSound implements ITickableSound {

    protected Entity entity;
    protected boolean isDone;

    public BoundSoundCore(ResourceLocation p_i45103_1_, Entity entity) {
        super(p_i45103_1_);
        this.entity = entity;
        this.field_147663_c = 1;
        this.field_147665_h = 0;
    }

    protected BoundSoundCore(ResourceLocation p_i45103_1_) {
        super(p_i45103_1_);
    }
}
