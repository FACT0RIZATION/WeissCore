package weissmoon.core.helper;

import net.minecraft.util.ResourceLocation;

    /**
        Returns a ResourcesLocation for mod.
     */
public class ResourceLocationHelper {
    
    public static ResourceLocation getResourceLocation(String modId, String path){
        return new ResourceLocation (modId, path);
    }

}
