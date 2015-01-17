package weissmoon.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

import java.lang.ref.WeakReference;

    /**
        Toggles PVP boolean.
     */

public class TogglePVP extends CommandBase {

    private WeakReference<MinecraftServer> server;

    public TogglePVP(MinecraftServer server)
    {
        this.server = new WeakReference(server);
    }

    @Override
    public String getCommandName() {
        return "togglepvp";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        boolean pvp = MinecraftServer.getServer().isPVPEnabled();
        MinecraftServer.getServer().setAllowPvp(!pvp);
        p_71515_1_.addChatMessage(new ChatComponentTranslation("weisscore.commands.pvp.success",  new Object[]{!pvp}));
    }
}
