package weissmoon.core.helper;

import net.minecraft.entity.player.EntityPlayer;

    /**
        Consumes one item in a specific slot.
     */

public class ConsumeItem {

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
}
