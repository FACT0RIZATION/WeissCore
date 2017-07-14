package weissmoon.core.api.client.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import weissmoon.core.api.client.item.IItemRenderer;

/**
 * Created by Weissmoon on 9/29/16.
 */
public interface IItemRenderCustom {

    @SideOnly(Side.CLIENT)
    public IItemRenderer getIItemRender();
}