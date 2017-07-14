package weissmoon.core.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import weissmoon.core.item.IItemWeiss;
import weissmoon.core.item.IMultiTexture;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Weissmoon on 7/22/16.
 */
public class ModelRegistryHelper {
    private List<Item> items;
    public void regItem(Item item){
        if(item instanceof IItemWeiss){
            items.add(item);
        }
    }

    void registerModels(){
        Item[] itemArray = (Item[]) items.toArray();
        for(Item item:itemArray){
            if (item instanceof IItemWeiss){
                if(item instanceof IMultiTexture){
                    ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
                    modelMesher.register(item, 0, new ModelResourceLocation((((IItemWeiss) item).getModID() + ":" + ((IItemWeiss) item).getWeissName())));
                }else{

                }
            }
        }
    }
}
