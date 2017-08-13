package weissmoon.core.client.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import weissmoon.core.WeissCore;
import weissmoon.core.helper.WeissItemRegistry;
import weissmoon.core.item.ICustomRender;
import weissmoon.core.item.IItemWeiss;
import weissmoon.core.item.WeissItem;

import java.util.List;

/**
 * Created by Weissmoon on 7/28/16.
 */
public class IIconRegister {

    public static final IIconRegister instance = new IIconRegister();

    private ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    public static final void registerWeissItemIcons() {
        List<Item> list = WeissItemRegistry.weissItemRegistry.getItemList();
        for (int i = 0; i < list.size(); i++) {
            IItemWeiss item = (IItemWeiss) list.get(i);
            if(!(item instanceof ICustomRender)) {
                instance.modelMesher.register(list.get(i), 0, new ModelResourceLocation(item.getModID().toLowerCase() + ":" + item.getWeissName(), "inventory"));
            }
        }
    }

    public void registerIcon(String resouceLocation){
    }
}
