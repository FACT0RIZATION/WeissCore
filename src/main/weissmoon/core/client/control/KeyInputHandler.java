package weissmoon.core.client.control;

        import org.lwjgl.input.Keyboard;
        import net.minecraft.client.Minecraft;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.ItemStack;
        import net.minecraftforge.fml.client.FMLClientHandler;
        import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
        import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
        import net.minecraftforge.fml.relauncher.Side;
        //import weissmoon.core.client.control.Keybinds;
        //import weissmoon.RWBY.item.itemBase.Moludes.*;
        //import weissmoon.RWBY.lib.Key;
        //import weissmoon.RWBY.network.*;
        import weissmoon.core.network.*;
        import weissmoon.core.network.PacketHandler;

@net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
public class KeyInputHandler{
//    private Key getPressedKeybinding (){
//        if (Keybinds.mode.isPressed()){
//            return Key.MODE;
//        }
//        return Key.UNKNOWN;
//    }

//    private Key getToggleKeybinding (){
//        int eventKey = Keyboard.getEventKey();
//        //if (eventKey == Keybinds.propel.getKeyCode()){
//            //return Key.PROPEL;
//        //}
//        return Key.UNKNOWN;
//    }

    public boolean isRWBYKey (){
        int eventKey = Keyboard.getEventKey();
        //if (eventKey == Keybinds.openslot.getKeyCode()){
            //return true;
        //}
        //if (eventKey == Keybinds.propel.getKeyCode()){
            //return true;
        //}
        return false;
    }

    @SubscribeEvent
    public void handleKeyInputEvent (KeyInputEvent event){
        if (!isRWBYKey()){
            return;
        }

        //Key toggleKeybinding = getToggleKeybinding();

        if (FMLClientHandler.instance().getClient().inGameHasFocus){
            if (FMLClientHandler.instance().getClientPlayerEntity() != null){
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
                PacketHandler.INSTANCE.sendToServer(new OpenHandPacket(Minecraft.getMinecraft().thePlayer.getName()));

//                if (entityPlayer.getCurrentEquippedItem() != null){
//                    boolean pressed = Keyboard.getEventKeyState();
//
//
//                    if ((Minecraft.getMinecraft().thePlayer != null) && (toggleKeybinding == Key.PROPEL)){
//                        PacketHandler.INSTANCE.sendToServer(new KeyPressedActionMessage(toggleKeybinding, pressed));
//                        KeyInputMap.getInputMapFor(entityPlayer.getCommandSenderName()).propelKey = pressed;
//                    }
//
//
//                    Key pressedKeybinding = getPressedKeybinding();
//
//                    if (pressedKeybinding == Key.UNKNOWN){
//                        return;
//                    }
//
//                    ItemStack currentlyEquippedItemStack = entityPlayer.getCurrentEquippedItem();
//
//                    if (((currentlyEquippedItemStack.getItem() instanceof IModes)) || ((currentlyEquippedItemStack.getItem() instanceof IModeWeaponDust))){
//                        if (entityPlayer.worldObj.isRemote){
//                            PacketHandler.INSTANCE.sendToServer(new KeyPressedActionMessage(pressedKeybinding));
//                        }
//                    }
//                }
            }
        }
    }
}