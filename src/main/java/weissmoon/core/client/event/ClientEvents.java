package weissmoon.core.client.event;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.input.Keyboard;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.helper.MaterialStringHelper;

public class ClientEvents{
    /**
     * Displays ItemBlock material and translates using .lang files
     * Also display food for wolves.
     */
    @SubscribeEvent
    public void tooltipEvent (ItemTooltipEvent event){
        if (((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54)))){
            Item item = event.getItemStack().getItem();
            if (ConfigurationHandler.materialEvent){
                if ((item != null) && ((item instanceof ItemBlock))){
                    TextComponentTranslation ds = new TextComponentTranslation("weisscore.material.material");
                    event.getToolTip().add(ds.getUnformattedText());
                    event.getToolTip().add(MaterialStringHelper.getMaterialString(((ItemBlock)item).block.getMaterial(((ItemBlock) item).getBlock().getStateFromMeta(item.getMetadata(event.getItemStack())))));
                }
            }
            if (ConfigurationHandler.wolfTip){
                if ((item != null) && ((item instanceof ItemFood))){
                    if (((ItemFood)item).isWolfsFavoriteMeat()){
                        TextComponentTranslation ds = new TextComponentTranslation("weisscore.wolfFood");
                        event.getToolTip().add(ds.getUnformattedText());
                    }
                }
            }
        }
    }

    //@SubscribeEvent
    public void recolorClouds (TickEvent.WorldTickEvent event){
    }
}
