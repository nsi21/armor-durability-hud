package com.armor.hud.mc.Config;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;

public class GetArmorDamage implements ClientModInitializer {
    public static int helmetDamageLeft, chestplateDamageLeft, leggingsDamageLeft, bootsDamageLeft;

    private int GetArmorStackDamage(int slot) {
        return MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.VALUES.get(slot)).getMaxDamage() -
                MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.VALUES.get(slot)).getDamage();
    }

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                assert client.player != null;

                helmetDamageLeft = GetArmorStackDamage(5);

                chestplateDamageLeft = GetArmorStackDamage(4);

                leggingsDamageLeft = GetArmorStackDamage(3);

                bootsDamageLeft = GetArmorStackDamage(2);

            } catch (Exception e) {
            }
        });

    }
}
