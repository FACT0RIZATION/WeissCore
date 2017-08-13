package weissmoon.core.item;

import net.minecraft.item.ItemStack;
import weissmoon.core.utils.NBTHelper;

public class CoreDummyItem extends WeissDummy{

    public CoreDummyItem(){
        super();
    }

    public ItemStack newDummyItemStack (String name, int size){
        ItemStack stack = new ItemStack(this, size);
        if (itemName.contains(name)){
            NBTHelper.setString(stack, ITEM_NAME_NBT, name);
        }
        return stack;
    }
}
