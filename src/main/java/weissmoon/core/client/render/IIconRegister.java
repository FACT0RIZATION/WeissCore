package weissmoon.core.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import weissmoon.core.client.render.renderOverride.CustomRenderRegistry;
import weissmoon.core.helper.WeissItemRegistry;
import weissmoon.core.api.client.item.IItemRenderCustom;
import weissmoon.core.item.IItemWeiss;

import java.util.List;

/**
 * Created by Weissmoon on 7/28/16.
 */
@SideOnly(Side.CLIENT)
public class IIconRegister {

    public static final IIconRegister INSTANCE = new IIconRegister();

    private ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

    public static final void registerWeissItemIcons() {
        List<Item> list = WeissItemRegistry.weissItemRegistry.getItemList();
        IItemWeiss item ;
        for (int i = 0; i < list.size(); i++) {
            item = (IItemWeiss) list.get(i);
            if(item instanceof IItemRenderCustom) {
                CustomRenderRegistry.registerItemRenderer((Item)item, ((IItemRenderCustom) item).getIItemRender());
            }
            item.registerIcons(INSTANCE);
        }
    }

    public IIcon registerIcon(Item item, String resouceLocation){
        if(!(item instanceof IItemWeiss))return null;
        if(resouceLocation.contains(":")){
            resouceLocation = resouceLocation.substring(resouceLocation.indexOf(":") + 1);
        }
        IIcon icon = new IIcon(((IItemWeiss)item).getModID().toLowerCase() + ":" + resouceLocation, "inventory");
        ModelLoader.registerItemVariants(item, icon);
        return icon;
    }
}
