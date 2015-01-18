package weissmoon.core.client.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabWeiss extends CreativeTabs {

    private String tabLabel;
    private Item tabItem;

    public CreativeTabWeiss(String label ,Item item) {
        super(label);
        this.tabLabel = label;
        this.tabItem = item;
    }

    @Override
    public Item getTabIconItem(){
        if (this.tabItem != null){
            return this.tabItem;
        }else{
            return Item.getItemById(1);
        }
    }

    @Override
    public String getTranslatedTabLabel(){
        if (this.tabLabel == null){
            return "entity.Item.name";
        }else{
            return this.tabLabel;
        }
    }
}
