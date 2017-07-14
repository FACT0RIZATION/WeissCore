package weissmoon.core.lib;

import net.minecraft.util.text.TextComponentTranslation;

/**
 * Hardcoded Vanilla material String vars.
 */
public class MaterialString{
    public static String AIR = "air";
    public static String GRASS = "grass";
    public static String GROUND = "ground";
    public static String WOOD = "wood";
    public static String ROCK = "rock";
    public static String IRON = "iron";
    public static String ANVIL = "anvil";
    public static String WATER = "water";
    public static String LAVA = "lava";
    public static String LEAVES = "leaves";
    public static String PLANTS = "plants";
    public static String VINE = "vine";
    public static String SPONGE = "sponge";
    public static String CLOTH = "cloth";
    public static String FIRE = "fire";
    public static String SAND = "sand";
    public static String CIRCUITS = "circuits";
    public static String CARPET = "carpet";
    public static String GLASS = "glass";
    public static String REDSTONE_LIGHT = "redstoneLight";
    public static String TNT = "tnt";
    public static String CORAL = "coral";
    public static String ICE = "ice";
    public static String PACKED_ICE = "packedIce";
    public static String SNOW = "snow";
    public static String CRAFTED_SNOW = "craftedSnow";
    public static String CACTUS = "cactus";
    public static String CLAY = "clay";
    public static String GOURD = "gourd";
    public static String DRAGON_EGG = "dragonEgg";
    public static String PORTAL = "portal";
    public static String CAKE = "cake";
    public static String WEB = "web";
    public static String PISTON = "piston";
    public static String BARRIER = "barrier";
    public static String UNKNOWN = "UNKNOWN";

    public static String getString (String string){
        TextComponentTranslation ds = new TextComponentTranslation("weisscore.material." + string, new Object[0]);
        return ds.getUnformattedText();
    }
}
