package com.armor.hud.mc.Config;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class GetItemDamage implements ClientModInitializer {
    public static volatile int currentItem, SecondHandItem;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                assert client.player != null;
                currentItem = client.player.getMainHandStack().getMaxDamage() - client.player.getMainHandStack().getDamage();
                SecondHandItem = client.player.getOffHandStack().getMaxDamage() - client.player.getOffHandStack().getDamage();
            } catch (NullPointerException e) {
            }
        });
    }
}
