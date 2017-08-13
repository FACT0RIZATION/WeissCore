package weissmoon.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;
import weissmoon.core.WeissCore;

public class SpawnBlockParticlesPacket implements IMessage, IMessageHandler<SpawnBlockParticlesPacket, IMessage>{

    int posX;
    int posY;
    int posZ;
    int dimID;
    public double xCoord;
    public double yCoord;
    public double zCoord;
    double xVel;
    double yVel;
    double zVel;
    boolean fancy;

    @Override
    public void fromBytes(ByteBuf buf){
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.dimID = buf.readInt();
        this.xCoord = buf.readDouble();
        this.yCoord = buf.readDouble();
        this.zCoord = buf.readDouble();
        this.xVel = buf.readDouble();
        this.yVel = buf.readDouble();
        this.zVel = buf.readDouble();
        this.fancy = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeInt(this.posZ);
        buf.writeInt(this.dimID);
        buf.writeDouble(this.xCoord);
        buf.writeDouble(this.yCoord);
        buf.writeDouble(this.zCoord);
        buf.writeDouble(this.xVel);
        buf.writeDouble(this.yVel);
        buf.writeDouble(this.zVel);
        buf.writeBoolean(this.fancy);
    }

    @Override
    public IMessage onMessage(SpawnBlockParticlesPacket message, MessageContext ctx){
        if(ctx.side == Side.CLIENT) {
            RayTraceResult movingobjectposition = Minecraft.getMinecraft().thePlayer.worldObj.
                    rayTraceBlocks(new Vec3d(
                            this.xCoord - this.xVel,
                            this.yCoord - this.yVel,
                            this.zCoord - this.zVel
                    ), new Vec3d(
                            this.xCoord + this.xVel,
                            this.yCoord + this.yVel,
                            this.zCoord + this.zVel
                    ));
            WeissCore.proxy.spawnBlockparticles(movingobjectposition, dimID, new Vec3d(xVel, yVel, zVel), fancy);
        }
        return null;
    }
}
