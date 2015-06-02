package weissmoon.core.compat;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import weissmoon.RWBY.entity.glyphs.IceGlyph;

public class RWBYCompat {

    public static boolean isIceGlyph(MovingObjectPosition movingObjectPosition){
        if(movingObjectPosition.entityHit instanceof IceGlyph){
            return true;
        }
        return false;
    }

    public static boolean isIceGlyph(Entity entity){
        if(entity instanceof IceGlyph){
            return true;
        }
        return false;
    }
}
