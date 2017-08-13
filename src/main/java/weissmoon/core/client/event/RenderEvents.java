package weissmoon.core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import weissmoon.core.client.render.renderOverride.CustomRenderRegistry;
import weissmoon.core.api.client.item.IItemRenderer;

/**
 * Created by Weissmoon on 9/21/16.
 */
public class RenderEvents {

    //
    @SubscribeEvent
    public void renderFirstPerson(RenderSpecificHandEvent event){
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack a = mc.thePlayer.getHeldItem(event.getHand());
        if(mc.thePlayer.getHeldItem(event.getHand()) == null)
            return;
        EnumHandSide side;
        if (mc.thePlayer.getPrimaryHand() == EnumHandSide.RIGHT){
            if (event.getHand() == EnumHand.MAIN_HAND) {
                side = EnumHandSide.RIGHT;
            }else{
                side = EnumHandSide.LEFT;
            }
        }else{
            if (event.getHand() == EnumHand.MAIN_HAND) {
                side = EnumHandSide.LEFT;
            }else{
                side = EnumHandSide.RIGHT;
            }
        }

        ItemCameraTransforms.TransformType renderSide = side == EnumHandSide.RIGHT
                ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND:
                ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND ;
        IItemRenderer itemRenderer = CustomRenderRegistry.getItemRenderer(mc.thePlayer.getHeldItem(event.getHand()), renderSide);
        if(itemRenderer == CustomRenderRegistry.getMissingRender()) {
            return;
        }
        event.setCanceled(true);
        GL11.glPushMatrix();
        try{
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef(0.0F, -0.8F, 0.0F);
            GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
            if(itemRenderer.shouldUseRenderHelper(renderSide, mc.thePlayer.getHeldItem(event.getHand()), IItemRenderer.ItemRendererHelper.EQUIPT_ANIMATION)){
                this.transformSideFirstPerson(side, event.getEquipProgress());
            }else{
                this.transformSideFirstPerson(side, 0.0F);
            }
            this.transformFirstPerson(side, event.getSwingProgress());
            this.renderItem(itemRenderer,renderSide, mc.thePlayer.getHeldItem(event.getHand()));
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }catch (Throwable e){
            GL11.glPopMatrix();
        }
    }

    public void renderItem(IItemRenderer itemRenderer, ItemCameraTransforms.TransformType type, ItemStack item, Object... data){
        itemRenderer.renderItem(type, item, data);
    }

    private void transformSideFirstPerson(EnumHandSide p_187459_1_, float p_187459_2_)
    {
        int i = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GL11.glTranslatef((float)i * 0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
    }

    private void transformFirstPerson(EnumHandSide p_187453_1_, float p_187453_2_)
    {
        int i = p_187453_1_ == EnumHandSide.RIGHT ? 1 : -1;
        if (p_187453_1_ == EnumHandSide.LEFT) {
            GL11.glTranslatef(-0.5890319049358367919921874F, -0.47985F, -1.8007F);
            GL11.glRotatef(20F, 0F, 1F, 0F);
        }else{
            GL11.glTranslatef(0.8279F, -0.47985F, -0.6183F);
            GL11.glRotatef(20F, 0F, 1F, 0F);
        }
        float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * (float)Math.PI);
        GL11.glRotatef((float)i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
        float f1 = MathHelper.sin(MathHelper.sqrt_float(p_187453_2_) * (float)Math.PI);
        GL11.glRotatef((float)i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((float)i * -45.0F, 0.0F, 1.0F, 0.0F);
    }

}
