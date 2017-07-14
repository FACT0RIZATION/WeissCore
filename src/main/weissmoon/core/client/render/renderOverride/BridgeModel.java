package weissmoon.core.client.render.renderOverride;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;
import weissmoon.core.api.client.item.IItemRenderer;
import weissmoon.core.api.client.item.IItemTieredRender;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.item.IItemWeiss;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

import static net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.*;

/**
 * Created by Weissmoon on 8/19/16.
 */
public class BridgeModel implements IBakedModel, IPerspectiveAwareModel {

    private DummyOverides dummyOverides;

    public BridgeModel(){
        this.dummyOverides = new DummyOverides();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return Collections.<BakedQuad>emptyList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ItemCameraTransforms getItemCameraTransforms() {
        ItemStack stack = this.dummyOverides.stack;
        IItemWeiss item = (IItemWeiss) stack.getItem();
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(
                item.getIcon(stack, MinecraftForgeClient.getRenderPass())).getItemCameraTransforms();
        //return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return this.dummyOverides;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        ItemStack stack = this.dummyOverides.stack;
        if (CustomRenderRegistry.getItemRenderer(stack, cameraTransformType).handleRenderType(stack, cameraTransformType)) {
            GL11.glPushMatrix();
            try {
                IItemRenderer renderer = CustomRenderRegistry.getItemRenderer(stack, cameraTransformType);
                if(cameraTransformType == THIRD_PERSON_LEFT_HAND || cameraTransformType == THIRD_PERSON_RIGHT_HAND) {
                    float f1 = 0;
                    float f13 = 0.8F;
                    GL11.glTranslatef(0.0F, 0.310F, -0.27F);
                    GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.38,0.38,0.38);
                }else if(cameraTransformType == FIRST_PERSON_RIGHT_HAND || cameraTransformType == FIRST_PERSON_LEFT_HAND){
                    return Pair.of(this.dummyOverides.getModel(), null);
                }
                if(renderer instanceof IItemTieredRender) {
                    boolean fancy = Minecraft.getMinecraft().gameSettings.fancyGraphics;
                    if (fancy && ConfigurationHandler.extraFancy) {
                        ((IItemTieredRender) renderer).renderVeryFancy(cameraTransformType, stack);
                    }else if(fancy){
                        ((IItemTieredRender) renderer).renderFancy(cameraTransformType, stack);
                    }
                }
                renderer.renderItem(cameraTransformType, stack);
            }finally {
                GL11.glPopMatrix();
            }
            return Pair.of(this.dummyOverides.getModel(), null);
        }else if(stack.getItem() instanceof IItemWeiss) {
            IItemWeiss item = (IItemWeiss) stack.getItem();
            if ((Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(item.getIcon(stack, MinecraftForgeClient.getRenderPass()))) !=
                    (Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getMissingModel())){
                return Pair.of(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(item.getIcon(stack, MinecraftForgeClient.getRenderPass()))
                        .getOverrides().handleItemState(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(item.getIcon(stack, MinecraftForgeClient.getRenderPass())), stack, this.dummyOverides.getWorld(), this.dummyOverides.livingBase), null);
            }
        }
        return Pair.of(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getMissingModel(), null);
    }

    class DummyOverides extends ItemOverrideList{

        private ItemStack stack;
        private IBakedModel model;
        private World world;
        private EntityLivingBase livingBase;
        public DummyOverides() {
            super(ImmutableList.<ItemOverride>of());
        }

        public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
        {
            this.stack = stack;
            this.model = originalModel;
            this.world = world;
            this.livingBase = entity;
            return originalModel;
        }
        public ItemStack getStack(){
            return stack;
        }
        public IBakedModel getModel(){
            return this.model;
        }
        public World getWorld(){
            return this.world;
        }
        public EntityLivingBase getEntity(){
            return this.livingBase;
        }
    }
}
