package com.armor.hud.mc.Gui;

import com.armor.hud.mc.Config.ArmorConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

import net.minecraft.text.Text;


public class Gui extends Screen {
    private final ArmorConfig config;

    public Gui(ArmorConfig config) {
        super(Text.empty());
        this.config = config;
    }

    private void OpenArmorGui(){
        ButtonWidget buttonWidgetOpenGuiOptionsArmor = ButtonWidget.builder(Text.of("Armor HUD settings"), (btn) -> {
            armorGui.show(config);
        }).dimensions(30, 80, 120, 20).build();
        this.addDrawableChild(buttonWidgetOpenGuiOptionsArmor);
    }

    private void ToggleInventoryHud(){
        ButtonWidget buttonWidgetToggleInventory = ButtonWidget.builder(Text.of("Toggle Inventory HUD"), (btn) -> {
            config.toggleInvHudEnabled();
            config.save();
        }).dimensions(30, 110, 120, 20).build();
        this.addDrawableChild(buttonWidgetToggleInventory);
    }


    public void GoBack(){

        ButtonWidget returnBack = ButtonWidget.builder(Text.of("<-"), (btn) -> {
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(20, 25, 35, 20).build();
        this.addDrawableChild(returnBack);
    }


    @Override
    protected void init() {
        OpenArmorGui();
        ToggleInventoryHud();
        GoBack();

//        ButtonWidget buttonWidgetOpenGuiOptionsDL = ButtonWidget.builder(Text.of("Armor HUD settings"), (btn) -> {
//            config.toggleEnabled();
//            config.save();
//        }).dimensions(30, 80, 120, 20).build();
//        this.addDrawableChild(buttonWidgetOpenGuiOptionsDL);
//        ButtonWidget buttonWidgetOpenGuiOptionsDL = ButtonWidget.builder(Text.of("Armor HUD settings"), (btn) -> {
//            config.toggleEnabled();
//            config.save();
//        }).dimensions(30, 80, 120, 20).build();
//        this.addDrawableChild(buttonWidgetOpenGuiOptionsDL);


    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawText(this.textRenderer, "Hud settings", 190, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);


    }

    public static void show(ArmorConfig config) {
        MinecraftClient.getInstance().setScreen(new Gui(config));
    }
}
