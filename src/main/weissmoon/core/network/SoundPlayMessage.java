package weissmoon.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class SoundPlayMessage implements IMessage, IMessageHandler<SoundPlayMessage,IMessage> {

    private String player;

    public SoundPlayMessage(){}
    public SoundPlayMessage(EntityPlayer entityPlayer){
        if(entityPlayer instanceof EntityPlayer){
            this.player = ((EntityPlayer) entityPlayer).getDisplayName();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readableBytes();
        this.player = buf.readBytes(length).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] noi = player.getBytes();
        buf.writeBytes(noi);
    }

    @Override
    public IMessage onMessage(SoundPlayMessage message, MessageContext ctx) {
        return null;
    }
}
