package weissmoon.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import weissmoon.core.client.event.BlockMaterialEvent;
import weissmoon.core.client.sound.BoundSoundCore;

public class ClientProxy extends CommonProxy {

    /**
        Plays BoundSound on client.
     */
    @Override
    public void playBoundSound(BoundSoundCore sound, Entity entity) {
        Minecraft.getMinecraft().getSoundHandler().playSound(sound);
    }

        /**
            Registers BlockMaterial tooltip for client.
         */
    @Override
    public void registerEventHandler() {
        MinecraftForge.EVENT_BUS.register(new BlockMaterialEvent());
    }

    public void initRenderer(){
        //MinecraftForgeClient.registerItemRenderer(ModItems.item, new DustRender());
        //MinecraftForgeClient.registerItemRenderer(ModItems.food, new DustRender());
    }

    public void nanoSounds(int soundID){}
}
