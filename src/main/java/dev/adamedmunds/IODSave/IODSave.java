package dev.adamedmunds.IODSave;


import com.google.gson.Gson;
import dev.adamedmunds.IODSave.JSON.JsonFileFormat;
import dev.adamedmunds.IODSave.JSON.LinkObject;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


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

    public static ArrayList<LinkObject> links = new ArrayList<LinkObject>();
    public static Gson gson = new Gson();
    public static File jsonFile;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        jsonFile = new File(event.getModConfigurationDirectory().getAbsolutePath() + "\\IODSave\\data.json");
        try {
            jsonFile.getParentFile().mkdirs();
            jsonFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new IODSave());
        ClientCommandHandler.instance.registerCommand(new SaveImage());
        ClientCommandHandler.instance.registerCommand(new SendImage());
        ClientCommandHandler.instance.registerCommand(new RemoveImage());
        this.LoadJson();
    }

    private void LoadJson() {
        try {
            FileReader reader = new FileReader(jsonFile);
            JsonFileFormat result = gson.fromJson(reader, JsonFileFormat.class);
            links = result.getLinks();
            for (LinkObject o: links) {
                System.out.println(o.getName() + " : " + o.getLink());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
