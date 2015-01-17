package weissmoon.core.client.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.input.Keyboard;
import weissmoon.core.helper.MaterialStringHelper;


    /**
        Displays ItemBlock material and translates using .lang files
     */

public class BlockMaterialEvent {

    @SubscribeEvent
    public void materialTooltipEvent(ItemTooltipEvent event){
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
            Item item = event.itemStack.getItem();
            if (item != null && item instanceof ItemBlock){
                ChatComponentTranslation ds = new ChatComponentTranslation("weisscore.material.material");
                event.toolTip.add(ds.getUnformattedTextForChat());
                event.toolTip.add(MaterialStringHelper.getMaterialString(((ItemBlock) item).field_150939_a.getMaterial()));
            }
        }
    }
}
