package dev.adamedmunds.IODSave;

import dev.adamedmunds.IODSave.JSON.JsonFileFormat;
import dev.adamedmunds.IODSave.JSON.LinkObject;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.FileWriter;

public class SaveImage extends ClientCommandBase {

    public SaveImage() {
        super("iodsave");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "Format: /iodsave <name> <link>");
            sender.addChatMessage(message);
            return;
        }

        String name = args[0];
        String link = args[1];
        for (LinkObject linkObject : IODSave.links) {
            if (linkObject.getName().equals(name)) {
                ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "That name already exists");
                sender.addChatMessage(message);
                return;
            }
        }

        LinkObject linkObject = new LinkObject();
        linkObject.setLink(link);
        linkObject.setName(name);
        IODSave.links.add(linkObject);

        JsonFileFormat fileFormat = new JsonFileFormat();
        fileFormat.setLinks(IODSave.links);

        try {
            FileWriter writer = new FileWriter(IODSave.jsonFile);
            IODSave.gson.toJson(fileFormat, writer);
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.AQUA + "Saved image: " + name);
            sender.addChatMessage(message);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
