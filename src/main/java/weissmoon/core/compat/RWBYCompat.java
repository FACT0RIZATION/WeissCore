package weissmoon.core.compat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import weissmoon.core.api.ICrystallizer;

public class RWBYCompat{
    public static boolean isEntityCrystallizer (RayTraceResult movingObjectPosition){
        return (movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof ICrystallizer);
    }

    public static boolean isIceGlyph (Entity entity){
        return (entity instanceof ICrystallizer);
    }


}