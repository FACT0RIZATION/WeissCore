package weissmoon.core.api.client.item;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public interface IItemTieredRender extends IItemRenderer{

    public void renderFancy (ItemCameraTransforms.TransformType type, ItemStack item, Object... data);

    public void renderVeryFancy (ItemCameraTransforms.TransformType type, ItemStack item, Object... data);
}
