package weissmoon.core.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class OreDictionaryHelper{
    public static String getOreName (ItemStack stack){
        int[] array = OreDictionary.getOreIDs(stack);
        if (0 < array.length){
            int e = array[0];
            return OreDictionary.getOreName(e);
        }
        return "Unknown";
    }

    public static boolean isOre (ItemStack stack, String oreName){
        if (stack == null || stack.getItem() == null){
            return false;
        }
        List<ItemStack> list = OreDictionary.getOres(oreName);
        for (ItemStack e : list){
            if ((stack.getItem() == e.getItem()) &&
                    (stack.getHasSubtypes()) &&
                    (stack.getItemDamage() != e.getItemDamage())){
                return true;
            }
        }
        return false;
    }

    public static boolean oreExists (String name){
        return !OreDictionary.getOres(name).isEmpty();
    }
}
