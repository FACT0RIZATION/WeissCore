package weissmoon.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import weissmoon.core.api.item.IModesCore;
import weissmoon.core.control.KeyInputMap;
import weissmoon.core.lib.Key;

public class KeyPressedActionMessage implements IMessage, IMessageHandler<KeyPressedActionMessage, IMessage>{
    private byte keyPress;
    private boolean pressed;

    public KeyPressedActionMessage (){
    }

    public KeyPressedActionMessage (Key key){
        if (key == Key.MODE){
            this.keyPress = ((byte)Key.MODE.ordinal());
        }
    }

    public KeyPressedActionMessage (Key key, boolean pressed){
        if (key == Key.PROPEL){
            this.keyPress = ((byte)Key.PROPEL.ordinal());
            this.pressed = pressed;
        }
    }

    public void fromBytes (ByteBuf buf){
        this.keyPress = buf.readByte();
        this.pressed = buf.readBoolean();
    }

    public void toBytes (ByteBuf buf){
        buf.writeByte(this.keyPress);
        buf.writeBoolean(this.pressed);
    }

    public IMessage onMessage (KeyPressedActionMessage message, MessageContext context){
        if (message.keyPress == Key.UNKNOWN.ordinal()){
            return null;
        }

        EntityPlayer entityPlayer = context.getServerHandler().playerEntity;

        if (message.keyPress == Key.PROPEL.ordinal()){
            KeyInputMap.getInputMapFor(entityPlayer.getName()).propelKey = message.pressed;
            return null;
        }

        if ((entityPlayer != null) && (entityPlayer.getHeldItem(EnumHand.MAIN_HAND) != null) && (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() != null)){
            if (message.keyPress == Key.MODE.ordinal()){
                if ((entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof IModesCore)){
                    ((IModesCore)entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem()).changeToolMode(entityPlayer.getHeldItem(EnumHand.MAIN_HAND));
                    return null;
                }
            }
        }
        return null;
    }
}
