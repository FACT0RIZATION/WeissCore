package weissmoon.core.client.render.renderOverride;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import weissmoon.core.api.client.item.IItemRenderer;
import weissmoon.core.helper.WeissItemRegistry;

import java.util.IdentityHashMap;

/**
 * Created by Weissmoon on 9/21/16.
 */
@SideOnly(Side.CLIENT)
public class CustomRenderRegistry {

    private static IdentityHashMap<Item, IItemRenderer> customItemRenderers = Maps.newIdentityHashMap();
    private static WeissOverrideMesh mesh = new WeissOverrideMesh();
    private static IItemRenderer noRenderer = new IItemRenderer() {
        @Override
        public boolean handleRenderType(ItemStack item, ItemCameraTransforms.TransformType cameraTransformType) {
            return false;
        }

        @Override
        public boolean shouldUseRenderHelper(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, ItemRendererHelper helper) {
            return false;
        }

        @Override
        public void renderItem(ItemCameraTransforms.TransformType cameraTransformType, ItemStack item, Object... data) {

        }

//        @Override
//        public WeissMesh getItemMeshDefinitions(ItemStack stack) {
//            return new WeissMesh() {
//                @Override
//                public ModelResourceLocation getModelLocation(ItemStack stack) {
//                    return new ModelResourceLocation("builtin/missing", "missing");
//                }
//            };
//        }
    };

    public static void registerItemRenderer(Item item, IItemRenderer renderer)
    {
        customItemRenderers.put(item, renderer);
    }

    public static IItemRenderer getItemRenderer(ItemStack item, ItemCameraTransforms.TransformType cameraTransformType)
    {
        Item item1 = item.getItem();
        IItemRenderer renderer = customItemRenderers.get(item1);
        if (renderer != null && renderer.handleRenderType(item, cameraTransformType))
        {
            return renderer;
        }
        return noRenderer;
    }

    public static int getRenderPass()
    {
        return MinecraftForgeClient.getRenderPass();
    }

    public static void registerToModelManager(){
//        for (Item e:itemList){
//            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(e, mesh);
//        }
        for(Item e: WeissItemRegistry.weissItemRegistry.getItemList()){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(e, mesh);
        }
    }

    public static IItemRenderer getMissingRender(){
        return noRenderer;
    }
}
