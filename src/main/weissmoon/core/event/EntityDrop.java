package weissmoon.core.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import weissmoon.core.helper.RNGHelper;

public class EntityDrop{
    @SubscribeEvent
    public void entityDrops (LivingDropsEvent event){
        if (((event.getEntityLiving() instanceof IMob)) &&
                ((event.getEntityLiving() instanceof net.minecraft.entity.monster.EntitySpider)) &&
                (RNGHelper.getRNGFloat() > 0.99D)){
            ItemStack itemStack = new ItemStack(Items.STRING);
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("silk_touch"), 1);
            event.getDrops().add(new EntityItem(event.getEntityLiving().worldObj, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, itemStack));
        }
    }
}
