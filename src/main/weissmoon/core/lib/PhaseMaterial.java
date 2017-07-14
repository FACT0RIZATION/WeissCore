package weissmoon.core.lib;

import net.minecraft.block.material.Material;

import java.util.*;

public class PhaseMaterial{

    private static final List<Material> defaultList = new ArrayList<Material>();

    static{
        defaultList.add(Material.PLANTS);
        defaultList.add(Material.LEAVES);
        defaultList.add(Material.WEB);
        defaultList.add(Material.VINE);
    }

    public static List<Material> addMaterial(List<Material> list, Material material){
        list.add(material);
        return list;
    }

    public static List<Material> removeMaterial(List<Material> list, Material material){
        int i = list.indexOf(material);
        while(i != -1) {
            list.remove(i);
            i = list.indexOf(material);
        }
        return list;
    }

    public static List getDefault(){
        return defaultList;
    }
}
