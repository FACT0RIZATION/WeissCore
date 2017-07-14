package weissmoon.core.api.client.item;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

/**
 * Created by Weissmoon on 9/21/16.
 */
public interface IItemRenderer {

    public enum ItemRendererHelper{
        /**
         * Determines if a rotation effect should be used when rendering an
         * EntityItem, like most default blocks do.
         */
        ENTITY_ROTATION,

        /**
         * Determines if an up-and-down bobbing effect should be used when
         * rendering an EntityItem, like most default items do.
         */
        ENTITY_BOBBING,
        /**
         * Determines if an item should have a rising animation, like most
         * default items do.
         */
        EQUIPT_ANIMATION,
        /**
         * Determines if an item should return additional model, like .OBJ models for rendering
         */
        RETURN_VERTEX
    }
    public boolean handleRenderType(ItemStack item, ItemCameraTransforms.TransformType cameraTransformType);
    public boolean shouldUseRenderHelper(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, ItemRendererHelper helper);
    public void renderItem(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, Object... data);
}
