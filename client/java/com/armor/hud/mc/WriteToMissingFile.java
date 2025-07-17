package com.armor.hud.mc;



import com.armor.hud.mc.Config.ArmorConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.util.log.Log;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteToMissingFile {

    public static void writeToFile(ArmorConfig config) {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("armor-hud-config.json");
        try (Reader reader = Files.newBufferedReader(path)) {
            reader.read();
        } catch (IOException e) {
            config.toggleEnabled();
            config.toggleInvHudEnabled();
            config.save();
        }
    }
}
