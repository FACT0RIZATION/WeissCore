package weissmoon.core.lib;

import net.minecraft.util.ResourceLocation;
import weissmoon.core.helper.ResourceLocationHelper;

public class TexturesCore{
    public static final String TEXTURE_LOCATION = "textures/";
    public static final String ITEM_LOCATION = TEXTURE_LOCATION + "items/";

    public static final ResourceLocation CRYSTAL_ALPHA = ResourceLocationHelper.getResourceLocation(ReferenceCore.MOD_ID.toLowerCase(), ITEM_LOCATION + "crystalAlpha.png");
    public static final ResourceLocation CRYSTAL_TEXTURE = ResourceLocationHelper.getResourceLocation(ReferenceCore.MOD_ID.toLowerCase(), ITEM_LOCATION + "crystalPixel.png");
    public static final ResourceLocation PLATE = ResourceLocationHelper.getResourceLocation(ReferenceCore.MOD_ID.toLowerCase(), ITEM_LOCATION + "missingPlate.png");
}
