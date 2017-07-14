package weissmoon.core.helper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import weissmoon.core.utils.NBTHelper;

public class InventoryHelper{
    public static boolean consumeItemInSlot (EntityPlayer player, int i, boolean consumeInCreative){
        if(player.capabilities.isCreativeMode) {
            if (consumeInCreative) {
                if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].stackSize > 0) {
                    if (--player.inventory.mainInventory[i].stackSize <= 0) {
                        player.inventory.mainInventory[i] = null;
                    }
                    player.inventoryContainer.detectAndSendChanges();
                }
            }
            return true;
        }else{
            if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].stackSize > 0) {
                if (--player.inventory.mainInventory[i].stackSize <= 0) {
                    player.inventory.mainInventory[i] = null;
                }
                player.inventoryContainer.detectAndSendChanges();
                return true;
            }
        }
        return false;
    }

    public static int[] getSlotsContainingItem (EntityPlayer player, Item item){
        int[] array = new int[0];
            for (int j = 0; j < player.inventory.mainInventory.length; j++){
                if ((player.inventory.mainInventory[j] != null) && (player.inventory.mainInventory[j].getItem() == item)){
                    array = new int[array.length + 1];
                    array[(array.length - 1)] = j;
                }
            }
        return array;
    }

    public static void givePlayerOrDropItemStack(ItemStack itemStack, EntityPlayer player){
        if (!player.inventory.addItemStackToInventory(itemStack)){
            //player.dropPlayerItemWithRandomChoice(itemStack, false);
            player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, itemStack));
        }
    }

    public static boolean hasItem(EntityPlayer player, Item item){
        for (int j = 0; j < player.inventory.mainInventory.length; j++){
            if ((player.inventory.mainInventory[j] != null) && (player.inventory.mainInventory[j].getItem() == item)){
                return true;
            }
        }
        return false;
    }

    public static boolean isPlayerInventoryFull(EntityPlayer player){
        for (int j = 0; j < player.inventory.mainInventory.length; j++){
            if ((player.inventory.mainInventory[j] == null)){
                return false;
            }
        }
        return true;
    }
}
