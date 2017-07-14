package weissmoon.core.client.render;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Map;

/**
 * Created by Weissmoon on 5/17/17.
 */
public class RenderHelper extends TileEntitySpecialRenderer {

    private static final Map<ResourceLocation, IBakedModel> bakedCache = Maps.newHashMap();
//
//    public void renderItemOBJModel(ResourceLocation resourceLocation, ResourceLocation texture){
//        IModel model = null;
//        try{
//            model = ModelLoaderRegistry.getModel(resourceLocation);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//        IBakedModel bakedModel = bakedCache.get(resourceLocation);
//        if(bakedModel = nul){
//            model.bake(TRSRTransformation.identity(), )
//        }
//        Minecraft.getMinecraft().getItemRenderer().re
//    }
}
