package weissmoon.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;

public class OpenHandPacket implements IMessage, IMessageHandler<OpenHandPacket, IMessage>{

    private String player;

    public OpenHandPacket(){}

    public OpenHandPacket(String playe){
        this.player = playe;
    }

    @Override
    public void fromBytes (ByteBuf buf){
        int length = buf.readableBytes();
        this.player = buf.readBytes(length).toString();
    }

    @Override
    public void toBytes (ByteBuf buf){
        String player1 = player;
        NetworkHelper.writeString(player1, buf);
    }

    @Override
    public IMessage onMessage (OpenHandPacket message, MessageContext ctx){
        if (ctx.side == Side.SERVER){
            //ctx.getServerHandler().playerEntity.inventory.currentItem = 0;
        }
        return null;
    }
}
