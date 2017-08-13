package weissmoon.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public abstract interface IProxy{
    public abstract void playBoundSound (String paramBoundSoundCore, SoundCategory category, Entity paramEntity, float loudness, float pitch);

    public abstract void registerEventHandler ();

    public abstract void initRenderer ();

    public abstract void nanoSounds (int paramInt);

    public abstract void spawnBlockparticles (RayTraceResult movingObjectPosition, int dimID, Vec3d vec3d, boolean fancyOnly);

    public abstract void registerKeyHandler();

    public void registerRenderOverride();
}
