package dev.adamedmunds.IODSave;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.json.simple.JSONObject;

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
        for (Object genericObject: IODSave.links) {
            JSONObject linkObject = (JSONObject) genericObject;
            if (linkObject.get("name").equals(name)) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(linkObject.get("link").toString());
            }
        }
    }
}
