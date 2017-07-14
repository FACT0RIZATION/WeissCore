package weissmoon.core.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.*;
import org.apache.logging.log4j.Level;
import weissmoon.core.WeissCore;
import weissmoon.core.client.render.IIcon;
import weissmoon.core.client.render.IIconRegister;
import weissmoon.core.lib.*;
import weissmoon.core.utils.NBTHelper;

import java.util.*;

import static weissmoon.core.utils.NBTHelper.getString;
import static weissmoon.core.utils.NBTHelper.hasTag;

public abstract class WeissDummy extends WeissItem {

    public static final String ITEM_NAME_NBT = "itemname";
    private static String unlocalizedName = Strings.DUMMY_ITEM_STRING;

    private final String classdomain = Loader.instance().activeModContainer().getModId();
    public List<String> itemName = new ArrayList();
    public HashMap<String, IIcon> iconMap = new HashMap();

    public WeissDummy() {
        super(Strings.DUMMY_ITEM_STRING);
        this.hasSubtypes = true;
    }

    public int getDamage(ItemStack stack)
    {
        if(NBTHelper.hasTag(stack, ITEM_NAME_NBT)) {
            String name = NBTHelper.getString(stack, ITEM_NAME_NBT);
            if (itemName.contains(name))
                return itemName.indexOf(name) + 5;
        }
        return 0;
    }

    /**
     *This method can only be called by the mod that created the subclass
     */
    public final void addDummyItem (String name){
        if(Loader.instance().activeModContainer().getModId().equals(this.classdomain)){
            if(itemName.contains(name)){
                WeissCore.instance.logger.log(Level.FATAL, "DummyItem name " + name + " has already been registered");
            }
            itemName.add(name);
        }
    }


    @Override
    public String getItemStackDisplayName (ItemStack itemStack){
        String prefix = null;
        String name = getString(itemStack, ITEM_NAME_NBT);
        StringBuilder unlocal = new StringBuilder();

        unlocal.append("item." + this.classdomain + ":");

        /*name*/
        if (name != ""){
            unlocal.append(name);
        }else{
            unlocal.append(unlocalizedName);
        }
        /*suffix*/
        unlocal.append(".name");

        return I18n.translateToLocal(unlocal.toString());
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister){
        this.itemIconWeiss = iconRegister.registerIcon(this, getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
        if (itemName.isEmpty()){
            return;
        }
        List<String> itemnames = itemName;
        for (String e : itemnames){
            this.iconMap.put(e, iconRegister.registerIcon(this, this.classdomain + ":" + e));
        }


    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getIconIndex (ItemStack stack){
//        if (hasTag(stack, ITEM_NAME_NBT)){
//            return iconMap.get(getString(stack, ITEM_NAME_NBT));
//        }
//        return this.itemIconWeiss;
//    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (ItemStack stack, int pass){
        if (hasTag(stack, ITEM_NAME_NBT)){
            return iconMap.get(getString(stack, ITEM_NAME_NBT));
        }
        return this.itemIconWeiss;
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getIconFromDamage (int p_77617_1_){
//        return this.itemIconWeiss;
//    }

    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs tab, List list){
        if (itemName.size() > 0){
            List<String> items = itemName;
            for (String i : items){
                ItemStack stack = new ItemStack(item, 1, 0);
                NBTHelper.setString(stack, ITEM_NAME_NBT, i);
                list.add(stack);
            }
        }
    }

    public CreativeTabs getCreativeTab (){
        return CreativeTabs.MISC;
    }


    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean flag){
        if (!(NBTHelper.hasTag(stack, ITEM_NAME_NBT))){
            String returning = "";
            TextComponentTranslation ds = new TextComponentTranslation("tt.item.weisscore:itemDummy");
            returning = ds.getUnformattedText();
            list.add(returning);
        }
    }

    public abstract ItemStack newDummyItemStack (String name, int size);
//    {
//        ItemStack stack = new ItemStack(this, size);
//        if (itemName.contains(name)){
//            NBTHelper.setString(stack, ITEM_NAME_NBT, name);
//        }
//        return stack;
//    }
    //public
}
