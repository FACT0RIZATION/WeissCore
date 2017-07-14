package weissmoon.core.test;

import weissmoon.core.api.client.item.IItemRenderCustom;
import weissmoon.core.api.client.item.IItemRenderer;
import weissmoon.core.item.WeissItem;

/**
 * Created by Weissmoon on 5/24/17.
 */
public class TestItem extends WeissItem{//} implements IItemRenderCustom {
    public TestItem() {
        super("test");
    }

//    @Override
//    public IItemRenderer getIItemRender() {
//        return new MelonHammerRenderer();
//    }
}
