package dev.adamedmunds.IODSave.Commands;

import dev.adamedmunds.IODSave.IODSave;
import dev.adamedmunds.IODSave.JSON.LinkObject;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SendImage extends ClientCommandBase {

    public SendImage() {
        super("iodsend");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "Format: /iodsend <name>");
            sender.addChatMessage(message);
            return;
        }

        String name = args[0];
        for (LinkObject linkObject : IODSave.links) {
            if (linkObject.getName().equals(name)) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(linkObject.getLink());
                return;
            }
        }
        ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "No image saved with: " + name);
        sender.addChatMessage(message);
    }
}
