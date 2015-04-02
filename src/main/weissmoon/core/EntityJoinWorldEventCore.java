package weissmoon.core;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import weissmoon.core.handler.ConfigurationHandler;

public class EntityJoinWorldEventCore {

    public void joinWorld(EntityJoinWorldEvent event){
        if(event.entity instanceof EntityItem){
            if(ConfigurationHandler.dealFire == 2 || ConfigurationHandler.dealFire == 3){
                //event.entity.isImmuneToFire = true;
            }
        }
    }
}
