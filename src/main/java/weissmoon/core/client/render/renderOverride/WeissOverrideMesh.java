package weissmoon.core.client.render.renderOverride;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import weissmoon.core.client.render.WeissMesh;
import weissmoon.core.lib.Strings;

/**
 * Created by Weissmoon on 9/27/16.
 */
public class WeissOverrideMesh implements WeissMesh {

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return new ModelResourceLocation(Strings.RESOURCE_PREFIX + "renderOverride", "inventory");
    }
}
