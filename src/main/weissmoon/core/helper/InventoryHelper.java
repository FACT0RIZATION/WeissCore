package weissmoon.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

    public static boolean consumeItemInSlot(EntityPlayer player, int i){
        if (player.inventory.mainInventory[i].stackSize > 0){
            if (--player.inventory.mainInventory[i].stackSize <= 0)
            {
                player.inventory.mainInventory[i] = null;
            }
            return true;
        }else{
            return false;
        }
    }

    public static int[] getSlotsContainingItem(EntityPlayer player, Item item){
        int[] array = new int[0];
        if (player.inventory.hasItem(item)){
            int j;
            for (j = 0; j < player.inventory.mainInventory.length; ++j){
                if (player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem() == item){
                    array = new int[array.length + 1];
                    array[array.length - 1] = j;
                }
            }
        }
        return array;
    }

    public static void givePlayerItemStack(ItemStack itemStack ,EntityPlayer player){
        if(!player.inventory.addItemStackToInventory(itemStack)){
            player.dropPlayerItemWithRandomChoice(itemStack, false);
        }
    }
}
