package mc.armorhudforge.utils;

import mc.armorhudforge.ArmorConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteToMissingFile {

    public static void writeToFile(ArmorConfig config) {
        Path path = FMLPaths.CONFIGDIR.get().resolve("armor-hud-config.json");
        try (Reader reader = Files.newBufferedReader(path)) {
            reader.read();
        } catch (IOException e) {
                config.toggleHud();
            config.toggleInvHud();

            config.save();
        }
    }
}
