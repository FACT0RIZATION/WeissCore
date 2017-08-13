package weissmoon.core.item;

import net.minecraft.init.*;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import weissmoon.core.WeissCore;
import weissmoon.core.lib.Strings;

public class CoreDummyItems{

    public static final WeissDummy dummyItem = new CoreDummyItem();

    public static final String lamp = "comparingLamp";

    public static void initDummyItems (){
        dummyItem.addDummyItem(lamp);
    }

    public static void initRecipes(){
        GameRegistry.addRecipe(new ShapedOreRecipe(WeissCore.newDummyItemStack(CoreDummyItems.lamp, 1), "rLg", " t ", 'r', "dyeRed", 'L', Blocks.REDSTONE_LAMP, 'g', "dyeGreen", 't', Items.COMPARATOR));
    }
}
