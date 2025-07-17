package mc.armorhudforge.events;

import mc.armorhudforge.hud.ArmorHud;
import mc.armorhudforge.gui.MainGui;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Armorhudforge.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void KeyPress(InputEvent.Key event) {
            assert Minecraft.getInstance().player != null;
            if (ArmorHud.Key.consumeClick()) {
                MainGui.open(Component.empty());
            }
        }

    }

    @Mod.EventBusSubscriber(modid = Armorhudforge.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyReg(RegisterKeyMappingsEvent event) {
            event.register(ArmorHud.Key);
        }

    }
}
