package weissmoon.core.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import weissmoon.core.client.render.IIcon;
import weissmoon.core.client.render.IIconRegister;

public interface IItemWeiss{
    String getModID();
    String getWeissName();
    @SideOnly(Side.CLIENT)
    IIcon getIcon (ItemStack stack, int pass);
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister);
}
