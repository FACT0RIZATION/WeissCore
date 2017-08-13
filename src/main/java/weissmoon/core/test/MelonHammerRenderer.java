package weissmoon.core.test;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import weissmoon.core.api.client.item.IItemRenderer;

/**
 * Created by Weissmoon on 5/22/17.
 */
public class MelonHammerRenderer implements IItemRenderer {
    //private MelonHammerModel model;

    public MelonHammerRenderer(){
        //this.model = new MelonHammerModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemCameraTransforms.TransformType cameraTransformType) {
        switch(cameraTransformType){
            case GUI:
                return false;
//            case GROUND:
//                return false;
        }
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, Object... data) {
        //this.model.render(0.0625F);
    }
}
