package weissmoon.core.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Weissmoon on 7/22/16.
 */
public interface IMultiTexture {
    @SideOnly(Side.CLIENT)
    public List<String> getTextures();
}
