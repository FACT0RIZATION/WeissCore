package weissmoon.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Loader;

import java.util.List;

/**
 * Created by Weissmoon on 11/13/16.
 */
public class WeissBlock extends Block implements IBlockWeiss {

    private final String ModId;
    protected final String RegName;

    public WeissBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        this.ModId = Loader.instance().activeModContainer().getModId();
        this.RegName = name;
    }

    public WeissBlock(String name, Material materialIn) {
        super(materialIn);
        this.ModId = Loader.instance().activeModContainer().getModId();
        this.RegName = name;
    }


    @Override
    public final String getModID() {
        return this.ModId;
    }

    public final String getWeissName(){
        return this.RegName;
    }
}
