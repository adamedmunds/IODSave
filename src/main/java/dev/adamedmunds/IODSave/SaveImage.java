package dev.adamedmunds.IODSave;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class SaveImage extends ClientCommandBase {

    public SaveImage() {
        super("iodsave");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2) {
            ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "Format: /iodsave <name> <link>");
            sender.addChatMessage(message);
            return;
        }

        String name = args[0];
        String link = args[1];
        System.out.println("links" + IODSave.links);
        for (Object genericObject: IODSave.links) {
            JSONObject linkObject = (JSONObject) genericObject;
            if (linkObject.get("name").equals(name)) {
                ChatComponentText message = new ChatComponentText(EnumChatFormatting.RED + "That name already exists");
                sender.addChatMessage(message);
                return;
            }
        }

        JSONObject linkObject = new JSONObject();
        linkObject.put("name", name);
        linkObject.put("link", link);
        IODSave.links.add(linkObject);

        try {
            URL file = IODSave.class.getClassLoader().getResource("data.json");
            FileWriter writer = new FileWriter(file.getFile());
            writer.write(IODSave.links.toJSONString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
