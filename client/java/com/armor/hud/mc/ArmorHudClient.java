package com.armor.hud.mc;

import com.armor.hud.mc.Config.ArmorConfig;
import net.fabricmc.api.ClientModInitializer;

public class ArmorHudClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WriteToMissingFile.writeToFile(ArmorConfig.load());
	}
}