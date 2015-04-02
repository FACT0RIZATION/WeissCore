package weissmoon.core.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import static weissmoon.core.helper.RNGHelper.getRNGFloat;

public class EntityDrop {

    @SubscribeEvent
    public void entityDrops(LivingDropsEvent event){
        if(event.entityLiving instanceof IMob){
            if(event.entityLiving instanceof EntitySpider){
                if (getRNGFloat() > 0.99){
                    ItemStack itemStack = new ItemStack(Items.string);
                    itemStack.addEnchantment(Enchantment.silkTouch, 1);
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, itemStack));
                }
            }
        }
    }
}
