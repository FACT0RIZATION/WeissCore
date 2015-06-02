package weissmoon.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemFood;
import net.minecraft.util.IIcon;

public class WeissItemFood extends ItemFood {

    protected IIcon[] itemIconArray;
    protected IIcon itemIconWeiss;

    public WeissItemFood(int point, float sat, boolean wolf) {
        super(point, sat, wolf);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.itemIconWeiss;
    }
}
