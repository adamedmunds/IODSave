package dev.adamedmunds.IODSave.Commands;

import dev.adamedmunds.IODSave.IODSave;
import dev.adamedmunds.IODSave.JSON.JsonFileFormat;
import dev.adamedmunds.IODSave.JSON.LinkObject;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.FileWriter;

public class RemoveImage extends ClientCommandBase {

    public RemoveImage() {
        super("iodremove");
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
                IODSave.links.remove(linkObject);
                JsonFileFormat fileFormat = new JsonFileFormat();
                fileFormat.setLinks(IODSave.links);
                try {
                    FileWriter writer = new FileWriter(IODSave.jsonFile);
                    IODSave.gson.toJson(fileFormat, writer);
                    writer.close();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "No image saved with: " + name);
        sender.addChatMessage(message);
    }
}
