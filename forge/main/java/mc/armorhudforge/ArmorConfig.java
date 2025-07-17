package mc.armorhudforge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mc.armorhudforge.gui.ArmorSettingsGui;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jline.utils.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ArmorConfig {
    private boolean HudOn, InvHudOn, toggleVerticalMode;
//            , cornerEnabled;
    private int armorX, armorY;
//        , armorCorner;


    public boolean isHudOn() {
        return HudOn;
    }

    public void toggleHud() {
        HudOn = !HudOn;
    }

    public boolean isInvHudOn() {
        return InvHudOn;
    }

    public void toggleInvHud() {
        this.InvHudOn = !InvHudOn;
    }

    public boolean isVerticalHudOn() {
        return toggleVerticalMode;
    }

    public void toggleVerticalHud() {
        this.toggleVerticalMode = !toggleVerticalMode;
    }

    public void setArmorX(){
        this.armorX = ArmorSettingsGui.hudX;
    }
    public int getArmorX() {
        return armorX;
    }

    public void setArmorY(){
        this.armorY = ArmorSettingsGui.hudY;
    }

    public int getArmorY() {
        return armorY;
    }

    public void save() {
        Path path = FMLPaths.CONFIGDIR.get().resolve("armor-hud-config.json");
        try (Writer writer = Files.newBufferedWriter(path)){
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(this, writer);
        } catch (IOException e) {
            Log.error("failed to write config file: {}", path.toFile(), e);
        }
    }

    public static ArmorConfig load(){
        Path path = FMLPaths.CONFIGDIR.get().resolve("armor-hud-config.json");
        try (Reader reader = Files.newBufferedReader(path)) {
            return new Gson()
                    .fromJson(reader, ArmorConfig.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");

        } catch (IOException e) {
            Log.error("failed to parse config file: {}", path.toFile(), e);
        }
        return new ArmorConfig();
    }
}
