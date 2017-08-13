package weissmoon.core.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IBoundSoundItem{

    public boolean isSoundDone(ItemStack itemStack, EntityPlayer player);
}
