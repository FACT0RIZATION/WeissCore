package weissmoon.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import weissmoon.core.client.event.ClientEvents;
import weissmoon.core.client.render.WeissRenderItem;
import weissmoon.core.client.render.renderOverride.BridgeModel;
import weissmoon.core.client.render.renderOverride.CustomRenderRegistry;
import weissmoon.core.client.render.IIconRegister;
import weissmoon.core.client.event.RenderEvents;
import weissmoon.core.client.sound.BoundSoundCore;
import weissmoon.core.control.KeyInputHandler;
import weissmoon.core.control.Keybinds;
import weissmoon.core.handler.HitParticleInit;
import weissmoon.core.helper.*;
import weissmoon.core.lib.Strings;

public class ClientProxy extends CommonProxy{

    public IBakedModel dustBa = new BridgeModel();

    public static final WeissRenderItem weissRenderItem = new WeissRenderItem();

    public void playBoundSound (String sound, SoundCategory category, Entity entity, float loudness, float pitch){
        ISound sound1 = new BoundSoundCore(ResourceLocationHelper.getResourceLocation(sound), category, entity, loudness, pitch);
        Minecraft.getMinecraft().getSoundHandler().playSound(sound1);
    }

    public void registerEventHandler (){
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    public void initRenderer (){
        IIconRegister.registerWeissItemIcons();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new RenderEvents());
    }

    public void nanoSounds (int soundID){
    }

    public void spawnBlockparticles (RayTraceResult movingObjectPosition, int dimID, Vec3d vec3d, boolean fancyOnly){
        if (!fancyOnly || (Minecraft.getMinecraft().gameSettings.fancyGraphics && fancyOnly))
        if (movingObjectPosition != null && movingObjectPosition.typeOfHit == RayTraceResult.Type.BLOCK){
            new HitParticleInit(
                    Minecraft.getMinecraft().theWorld,
                    movingObjectPosition.hitVec.xCoord,
                    movingObjectPosition.hitVec.yCoord,
                    movingObjectPosition.hitVec.zCoord,
                    vec3d.xCoord,
                    vec3d.yCoord,
                    vec3d.zCoord,
                    Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(movingObjectPosition.hitVec)));
        }
    }

    @Override
    public void registerKeyHandler(){
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        ClientRegistry.registerKeyBinding(Keybinds.mode);
        ClientRegistry.registerKeyBinding(Keybinds.propel);
        //ClientRegistry.registerKeyBinding(Keybinds.openslot);
    }

    public void registerRenderOverride(){
        CustomRenderRegistry.registerToModelManager();
    }

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent modelBakeEvent){
        //modelBakeEvent.getModelRegistry().putObject(new ModelResourceLocation(Strings.RESOURCE_PREFIX.toLowerCase() + "model/itemCrystal.obj"), this.dustBa);
        modelBakeEvent.getModelRegistry().putObject(new ModelResourceLocation(Strings.RESOURCE_PREFIX + "renderOverride", "inventory"), this.dustBa);
        modelBakeEvent.getModelRegistry();
//        for (ModelResourceLocation e: modelBakeEvent.getModelRegistry().getKeys()){
//            if (modelBakeEvent.getModelRegistry().getObject(e) == modelBakeEvent.getModelManager().getMissingModel()){
//                modelBakeEvent.getModelRegistry().putObject(e, modelBakeEvent.getModelManager().getModel(new ModelResourceLocation("" , "inventory")));
//            }
//            if (modelBakeEvent.getModelRegistry().getObject(e).getParticleTexture() == Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite()){
//                modelBakeEvent.getModelRegistry().putObject(e, modelBakeEvent.getModelManager().getModel(new ModelResourceLocation("" , "inventory")));
//            }
//        }
    }
}
