package weissmoon.core.proxy;

import net.minecraft.entity.Entity;
import weissmoon.core.client.sound.BoundSoundCore;

public interface IProxy {

    public void playBoundSound(BoundSoundCore sound, Entity entity);

    public void registerEventHandler();

    public void initRenderer();

    public void nanoSounds(int soundID);
}
