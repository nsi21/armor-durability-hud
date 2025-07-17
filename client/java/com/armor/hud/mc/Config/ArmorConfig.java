package com.armor.hud.mc.Config;

import com.armor.hud.mc.Gui.armorGui;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ArmorConfig {

    private static final Logger log = LoggerFactory.getLogger(ArmorConfig.class);

    private boolean HudOn, InvHudOn, toggleVerticalMode, cornerEnabled;
    private int armorX, armorY, armorCorner;


    public boolean isHudOn() {
        return HudOn;
    }

    public void toggleEnabled() {
        this.HudOn = !HudOn;
    }

    public boolean isInvHudOn() {
        return InvHudOn;
    }

    public void toggleInvHudEnabled() {
        this.InvHudOn = !InvHudOn;
    }

    public boolean isVerticalModeOn() {
        return toggleVerticalMode;
    }

    public void toggleVerticalMode() {
        this.toggleVerticalMode = !toggleVerticalMode;
    }

    public void setArmorX(){
        this.armorX = armorGui.HudX;
    }
    public int armorX() {
        return armorX;
    }

    public void setArmorY(){
        this.armorY = armorGui.HudY;
    }

    public int armorY() {
        return armorY;
    }

    public void setArmorCorner(){
        this.armorCorner = armorGui.corner;
    }

    public int armorCorner(){
        return armorCorner;
    }

    public boolean isCornerModeOn(){
        return cornerEnabled;
    }
    public void toggleCornerEnabled() {
        this.cornerEnabled = !cornerEnabled;
    }


    public void save() {
        Path path = FabricLoader.getInstance()
                .getConfigDir().resolve("armor-hud-config.json");
        try (Writer writer = Files.newBufferedWriter(path)) {
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create().toJson(this, writer);
        } catch (IOException e) {
            log.error("failed to write config file: {}", path.toFile(), e);
        }
    }

    public static ArmorConfig load() {
        Path path = FabricLoader.getInstance()
                .getConfigDir().resolve("armor-hud-config.json");
        try (Reader reader = Files.newBufferedReader(path)) {
            return new Gson().fromJson(reader, ArmorConfig.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");

        } catch (IOException e) {
            log.error("failed to parse config file: {}", path.toFile(), e);
        }
        return new ArmorConfig();
    }

}
