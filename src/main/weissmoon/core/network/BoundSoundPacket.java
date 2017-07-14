package weissmoon.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.SoundCategory;
import org.apache.commons.lang3.Validate;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;
import weissmoon.core.WeissCore;

public class BoundSoundPacket implements IMessage, IMessageHandler<BoundSoundPacket, IMessage>{
    private String player;
    private String soundName;
    private String category;
    private float loudness;
    private float pitch;

    public BoundSoundPacket (EntityPlayer entityPlayer, SoundCategory category, String soundName, float loudness, float pitch){
        if ((entityPlayer != null)){
            Validate.notNull(soundName, "boundSoundName");
            this.player = entityPlayer.getName();
            this.soundName = soundName;
            this.category = category.getName();
            this.loudness = loudness;
            this.pitch = pitch;
        }
    }

    public void fromBytes (ByteBuf buf){
        int length = buf.readShort();
        this.player = buf.readBytes(length).toString();
        length = buf.readShort();
        this.soundName = buf.readBytes(length).toString();
        length = buf.readShort();
        this.category = buf.readBytes(length).toString();
        this.loudness = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    public void toBytes (ByteBuf buf){
        String player1 = this.player;
        String sound = this.soundName;
        NetworkHelper.writeString(player1, buf);
        NetworkHelper.writeString(sound, buf);
        buf.writeFloat(this.loudness);
        buf.writeFloat(this.pitch);
    }

    public IMessage onMessage (BoundSoundPacket message, MessageContext ctx){
        if (ctx.side == Side.CLIENT){
            EntityPlayer s = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(this.player);
            WeissCore.proxy.playBoundSound(this.soundName, SoundCategory.getByName(this.category), s, this.loudness, this.pitch);
        }
        return null;
    }
}
