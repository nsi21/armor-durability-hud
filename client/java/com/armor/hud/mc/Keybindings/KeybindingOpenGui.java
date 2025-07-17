package com.armor.hud.mc.Keybindings;

import com.armor.hud.mc.Gui.Gui;
import com.armor.hud.mc.Config.ArmorConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
public class KeybindingOpenGui implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ArmorConfig config = ArmorConfig.load();

        KeyBinding openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open Armor Gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "Armor HUD Keybindings"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGui.wasPressed()){
                Gui.show(config);
            }
        });

        }
}
