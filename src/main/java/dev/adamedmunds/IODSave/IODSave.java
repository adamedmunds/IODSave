package dev.adamedmunds.IODSave;


import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;


@Mod(
        modid = IODSave.MODID,
        version = IODSave.VERSION,
        name = IODSave.NAME,
        acceptedMinecraftVersions = "[1.8.9]",
        clientSideOnly = true
)
public class IODSave {

    public static final String NAME = "IODSave";
    public static final String MODID = "iodsave";
    public static final String VERSION = "1.0";

    public static JSONArray links = new JSONArray();
    public static String path;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        path = event.getSourceFile().getAbsolutePath();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new IODSave());
        ClientCommandHandler.instance.registerCommand(new SaveImage());
        ClientCommandHandler.instance.registerCommand(new SendImage());
        ClientCommandHandler.instance.registerCommand(new RemoveImage());
        this.LoadJson();
    }

    private void LoadJson(){
        try {
            JSONParser jsonParser = new JSONParser();
            URL file = IODSave.class.getClassLoader().getResource("data.json");
            FileReader reader = new FileReader(file.getFile());
            Object obj = jsonParser.parse(reader);
            IODSave.links = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
