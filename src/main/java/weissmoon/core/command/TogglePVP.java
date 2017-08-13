package weissmoon.core.command;

import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.lang.ref.WeakReference;

/**
 * Toggles PVP boolean.
 */

public class TogglePVP extends CommandBase{
    private WeakReference<MinecraftServer> server;

    public TogglePVP (MinecraftServer server){
        this.server = new WeakReference(server);
    }

    public String getCommandName (){
        return "togglepvp";
    }

    public String getCommandUsage (ICommandSender p_71518_1_){
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean pvp = server.isPVPEnabled();
        server.setAllowPvp(!pvp);
        sender.addChatMessage(new TextComponentTranslation("weisscore.commands.pvp.success", new Object[]{Boolean.valueOf(!pvp)}));
    }

    public int getRequiredPermissionLevel (){
        return 2;
    }

    public void processCommand (ICommandSender p_71515_1_, String[] p_71515_2_){
        //boolean pvp = MinecraftServer.getServer().isPVPEnabled();
        //MinecraftServer.getServer().setAllowPvp(!pvp);
        //p_71515_1_.addChatMessage(new ChatComponentTranslation("weisscore.commands.pvp.success", new Object[]{Boolean.valueOf(!pvp)}));
    }
}
