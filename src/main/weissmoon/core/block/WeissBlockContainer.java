package weissmoon.core.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Weissmoon on 11/13/16.
 */
public abstract class WeissBlockContainer extends BlockContainer implements IBlockWeiss {

    private final String ModId;
    protected final String RegName;

    protected WeissBlockContainer(String name, Material materialIn) {
        super(materialIn);
        this.ModId = Loader.instance().activeModContainer().getModId();
        this.RegName = name;
    }

    protected WeissBlockContainer(String name, Material materialIn, MapColor color) {
        super(materialIn, color);
        this.ModId = Loader.instance().activeModContainer().getModId();
        this.RegName = name;
    }

    @Override
    abstract public TileEntity createNewTileEntity(World worldIn, int meta);

    @Override
    public final String getModID() {
        return this.ModId;
    }

    @Override
    public final String getWeissName(){
        return this.RegName;
    }
}
