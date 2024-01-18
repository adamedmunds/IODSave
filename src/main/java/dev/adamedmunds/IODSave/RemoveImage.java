package dev.adamedmunds.IODSave;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RemoveImage extends ClientCommandBase {

    public RemoveImage() {
        super("iodremove");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "Format: /iodsend <name>");
            sender.addChatMessage(message);
            return;
        }

        String name = args[0];
        JSONObject tempRemoveObject = new JSONObject();
        for (Object genericObject: IODSave.links) {
            JSONObject linkObject = (JSONObject) genericObject;
            if (linkObject.get("name").equals(name)) {
               tempRemoveObject.put("name", name);
               tempRemoveObject.put("link", linkObject.get("link"));
                IODSave.links.remove(tempRemoveObject);
                try {
                    URL file = IODSave.class.getClassLoader().getResource("data.json");
                    FileWriter writer = new FileWriter(file.getFile());
                    writer.write(IODSave.links.toJSONString());
                    writer.flush();
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
