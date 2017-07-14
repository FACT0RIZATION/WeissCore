package weissmoon.core.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import weissmoon.core.handler.ConfigurationHandler;

public class EntityJoinWorldEventCore{

    public void joinWorld (EntityJoinWorldEvent event){
        if (event.getEntity() instanceof EntityItem){
            if (ConfigurationHandler.dealFire == 2 || ConfigurationHandler.dealFire == 3){
                event.getEntity().fireResistance = -9;
            }
        }
    }
}