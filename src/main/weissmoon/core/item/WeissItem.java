package weissmoon.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class WeissItem extends Item {

    protected IIcon[] itemIconArray;
    protected IIcon itemIconWeiss;


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.itemIconWeiss;
    }

}
