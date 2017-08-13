package weissmoon.core.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.*;
import weissmoon.core.client.render.IIcon;
import weissmoon.core.client.render.IIconRegister;
import weissmoon.core.helper.WeissItemRegistry;

public class WeissItemFood extends ItemFood implements IItemWeiss{
    protected IIcon[] itemIconArray;
    protected IIcon itemIconWeiss;
    private final String ModId;
    protected final String RegName;

    public WeissItemFood (int point, float sat, boolean wolf, String name){
        super(point, sat, wolf);
        this.ModId = Loader.instance().activeModContainer().getModId();
        this.RegName = name;
        this.setUnlocalizedName(this.ModId.toLowerCase() + ":" + this.RegName);
        this.setRegistryName(this.ModId.toLowerCase() + ":" + this.RegName);
        WeissItemRegistry.weissItemRegistry.regItem(this);
    }

    @Override
    public final String getModID() {
        return this.ModId;
    }

    public final String getWeissName(){
        return this.RegName;
    }

//    @SideOnly(Side.CLIENT)
//    public IIcon getIconFromDamage (int p_77617_1_){
//        return this.itemIconWeiss;
//    }

    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister){
        this.itemIconWeiss = iconRegister.registerIcon(this, getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
    }

    /**
     * Use for icon arrays(Held item)
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (ItemStack stack, int pass){
        return this.itemIconWeiss;
    }

}
