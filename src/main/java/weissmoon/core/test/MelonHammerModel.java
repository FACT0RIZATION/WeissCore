package weissmoon.core.test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;
import weissmoon.core.client.render.WeissRenderItem;
import weissmoon.core.proxy.ClientProxy;

/**
 * Created by Weissmoon on 5/22/17.
 */
public class MelonHammerModel extends ModelBase {

    ModelRenderer Shape1;

    public MelonHammerModel() {
        this.textureWidth = 4;
        this.textureHeight = 2;


        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(4, 2);
        this.Shape1.mirror = true;
        setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
    }

    public void render (float f5){
        GL11.glPushMatrix();
        this.Shape1.render(f5);
        IBakedModel melon = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation("minecraft:melon_block#inventory"));
        //FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(melon.getParticleTexture().getIconName()));
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        ClientProxy.weissRenderItem.renderModel(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation(
                        "minecraft:melon_block#inventory")), -1,
                //new ItemStack(Blocks.MELON_BLOCK));
                null);
        GL11.glPopMatrix();
    }


    private void setRotation (ModelRenderer model, float x, float y, float z){
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
