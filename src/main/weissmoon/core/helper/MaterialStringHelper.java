package weissmoon.core.helper;

import net.minecraft.block.material.Material;
import weissmoon.core.lib.MaterialString;

    /**
        List of Vanilla materials.
        Currently hard coded.
     */

public class MaterialStringHelper {

    public static String getMaterialString(Material material){
        if (material == Material.air){
            return MaterialString.getString(MaterialString.AIR);
        }else if (material == Material.grass){
            return MaterialString.getString(MaterialString.GRASS);
        }else if (material == Material.ground){
            return MaterialString.getString(MaterialString.GROUND);
        }else if (material == Material.wood){
            return MaterialString.getString(MaterialString.WOOD);
        }else if (material == Material.rock){
            return MaterialString.getString(MaterialString.ROCK);
        }else if (material == Material.iron){
            return MaterialString.getString(MaterialString.IRON);
        }else if (material == Material.anvil){
            return MaterialString.getString(MaterialString.ANVIL);
        }else if (material == Material.water){
            return MaterialString.getString(MaterialString.WATER);
        }else if (material == Material.lava){
            return MaterialString.getString(MaterialString.LAVA);
        }else if (material == Material.leaves){
            return MaterialString.getString(MaterialString.LEAVES);
        }else if (material == Material.plants){
            return MaterialString.getString(MaterialString.PLANTS);
        }else if (material == Material.vine){
            return MaterialString.getString(MaterialString.VINE);
        }else if (material == Material.sponge){
            return MaterialString.getString(MaterialString.SPONGE);
        }else if (material == Material.cloth){
            return MaterialString.getString(MaterialString.CLOTH);
        }else if (material == Material.fire){
            return MaterialString.getString(MaterialString.FIRE);
        }else if (material == Material.sand){
            return MaterialString.getString(MaterialString.SAND);
        }else if (material == Material.circuits){
            return MaterialString.getString(MaterialString.CIRCUITS);
        }else if (material == Material.carpet){
            return MaterialString.getString(MaterialString.CARPET);
        }else if (material == Material.glass){
            return MaterialString.getString(MaterialString.GLASS);
        }else if (material == Material.redstoneLight){
            return MaterialString.getString(MaterialString.REDSTONE_LIGHT);
        }else if (material == Material.tnt){
            return MaterialString.getString(MaterialString.TNT);
        }else if (material == Material.coral){
            return MaterialString.getString(MaterialString.CORAL);
        }else if (material == Material.ice){
            return MaterialString.getString(MaterialString.ICE);
        }else if (material == Material.packedIce){
            return MaterialString.getString(MaterialString.PACKED_ICE);
        }else if (material == Material.snow){
            return MaterialString.getString(MaterialString.SNOW);
        }else if (material == Material.craftedSnow){
            return MaterialString.getString(MaterialString.CRAFTED_SNOW);
        }else if (material == Material.cactus){
            return MaterialString.getString(MaterialString.CACTUS);
        }else if (material == Material.clay){
            return MaterialString.getString(MaterialString.CLAY);
        }else if (material == Material.gourd){
            return MaterialString.getString(MaterialString.GOURD);
        }else if (material == Material.dragonEgg){
            return MaterialString.getString(MaterialString.DRAGON_EGG);
        }else if (material == Material.portal){
            return MaterialString.getString(MaterialString.PORTAL);
        }else if (material == Material.cake){
            return MaterialString.getString(MaterialString.CAKE);
        }else if (material == Material.web){
            return MaterialString.getString(MaterialString.WEB);
        }else if (material == Material.piston){
            return MaterialString.getString(MaterialString.PISTON);
        }
        return MaterialString.getString(MaterialString.UNKNOWN);
    }
}
