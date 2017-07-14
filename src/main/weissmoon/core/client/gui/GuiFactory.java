package weissmoon.core.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

public class GuiFactory implements IModGuiFactory{
    public void initialize (Minecraft minecraft){
    }

    public Class<? extends GuiScreen> mainConfigGuiClass (){
        return ModGuiConfig.class;
    }

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories (){
        return null;
    }

    public RuntimeOptionGuiHandler getHandlerFor (RuntimeOptionCategoryElement element){
        return null;
    }
}
