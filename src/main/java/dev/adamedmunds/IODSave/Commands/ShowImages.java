package dev.adamedmunds.IODSave.Commands;

import dev.adamedmunds.IODSave.IODSave;
import dev.adamedmunds.IODSave.JSON.LinkObject;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ShowImages extends ClientCommandBase {

    public ShowImages() {
        super("iodlist");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 0) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "Format: /iodlist");
            sender.addChatMessage(message);
            return;
        }

        for (LinkObject link:IODSave.links) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.AQUA + link.getName() + " : " + link.getLink());
            sender.addChatMessage(message);
        }
    }
}
