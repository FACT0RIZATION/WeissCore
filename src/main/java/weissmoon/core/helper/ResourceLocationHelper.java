package weissmoon.core.helper;

import org.apache.commons.lang3.Validate;
import net.minecraft.util.ResourceLocation;

/**
 * Returns a ResourcesLocation for mod.
 */
public class ResourceLocationHelper{
    public static ResourceLocation getResourceLocation (String modId, String path){
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation (String path){
        Validate.notNull(path);
        String domain = path.substring(0, path.indexOf(":"));
        String resource = path.substring(path.indexOf(":") + 1);

        return new ResourceLocation(domain, resource);
    }
}
