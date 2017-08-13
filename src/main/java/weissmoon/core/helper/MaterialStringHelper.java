package weissmoon.core.helper;

import net.minecraft.block.material.Material;
import weissmoon.core.lib.MaterialString;

/**
 * List of Vanilla materials.
 */
public class MaterialStringHelper{
    public static String getMaterialString (Material material){
        if (material == Material.AIR){
            return MaterialString.getString(MaterialString.AIR);
        }
        if (material == Material.GRASS){
            return MaterialString.getString(MaterialString.GRASS);
        }
        if (material == Material.GROUND){
            return MaterialString.getString(MaterialString.GROUND);
        }
        if (material == Material.WOOD){
            return MaterialString.getString(MaterialString.WOOD);
        }
        if (material == Material.ROCK){
            return MaterialString.getString(MaterialString.ROCK);
        }
        if (material == Material.IRON){
            return MaterialString.getString(MaterialString.IRON);
        }
        if (material == Material.ANVIL){
            return MaterialString.getString(MaterialString.ANVIL);
        }
        if (material == Material.WATER){
            return MaterialString.getString(MaterialString.WATER);
        }
        if (material == Material.LAVA){
            return MaterialString.getString(MaterialString.LAVA);
        }
        if (material == Material.LEAVES){
            return MaterialString.getString(MaterialString.LEAVES);
        }
        if (material == Material.PLANTS){
            return MaterialString.getString(MaterialString.PLANTS);
        }
        if (material == Material.VINE){
            return MaterialString.getString(MaterialString.VINE);
        }
        if (material == Material.SPONGE){
            return MaterialString.getString(MaterialString.SPONGE);
        }
        if (material == Material.CLOTH){
            return MaterialString.getString(MaterialString.CLOTH);
        }
        if (material == Material.FIRE){
            return MaterialString.getString(MaterialString.FIRE);
        }
        if (material == Material.SAND){
            return MaterialString.getString(MaterialString.SAND);
        }
        if (material == Material.CIRCUITS){
            return MaterialString.getString(MaterialString.CIRCUITS);
        }
        if (material == Material.CARPET){
            return MaterialString.getString(MaterialString.CARPET);
        }
        if (material == Material.GLASS){
            return MaterialString.getString(MaterialString.GLASS);
        }
        if (material == Material.REDSTONE_LIGHT){
            return MaterialString.getString(MaterialString.REDSTONE_LIGHT);
        }
        if (material == Material.TNT){
            return MaterialString.getString(MaterialString.TNT);
        }
        if (material == Material.CORAL){
            return MaterialString.getString(MaterialString.CORAL);
        }
        if (material == Material.ICE){
            return MaterialString.getString(MaterialString.ICE);
        }
        if (material == Material.PACKED_ICE){
            return MaterialString.getString(MaterialString.PACKED_ICE);
        }
        if (material == Material.SNOW){
            return MaterialString.getString(MaterialString.SNOW);
        }
        if (material == Material.CRAFTED_SNOW){
            return MaterialString.getString(MaterialString.CRAFTED_SNOW);
        }
        if (material == Material.CACTUS){
            return MaterialString.getString(MaterialString.CACTUS);
        }
        if (material == Material.CLAY){
            return MaterialString.getString(MaterialString.CLAY);
        }
        if (material == Material.GOURD){
            return MaterialString.getString(MaterialString.GOURD);
        }
        if (material == Material.DRAGON_EGG){
            return MaterialString.getString(MaterialString.DRAGON_EGG);
        }
        if (material == Material.PORTAL){
            return MaterialString.getString(MaterialString.PORTAL);
        }
        if (material == Material.CAKE){
            return MaterialString.getString(MaterialString.CAKE);
        }
        if (material == Material.WEB){
            return MaterialString.getString(MaterialString.WEB);
        }
        if (material == Material.PISTON){
            return MaterialString.getString(MaterialString.PISTON);
        }
        if (material == Material.BARRIER){
            return MaterialString.getString(MaterialString.BARRIER);
        }
        return MaterialString.getString(MaterialString.UNKNOWN);
    }
}
