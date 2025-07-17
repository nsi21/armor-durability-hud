package com.armor.hud.mc.Gui;

import com.armor.hud.mc.Config.ArmorConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class armorGui extends Screen {
    public static volatile int HudX, HudY;
    public static int corner = ArmorConfig.load().armorCorner();


    public void onlyNumberToast() {
        assert this.client != null;
        this.client.getToastManager().add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("NOTICE!"), Text.of("Only Use Numbers!"))
        );
    }

    private final ArmorConfig config;

    public armorGui(
            ArmorConfig config) {
        super(Text.empty());
        this.config = config;
    }

    private void ToggleArmorHudButton(){
        ButtonWidget ToggleArmorHud = ButtonWidget.builder(Text.of("Toggle armor hud"), (btn) -> {
            config.toggleEnabled();
            config.save();
        }).dimensions(30, 80, 120, 20).build();
        this.addDrawableChild(ToggleArmorHud);
    }

    private void ToggleVerticalMode(){
        ButtonWidget toggleVerticalMode = ButtonWidget.builder(Text.of("Toggle Vertical mode"), (btn) -> {
            config.toggleVerticalMode();
            config.save();
        }).dimensions(30, 110, 120, 20).build();
        this.addDrawableChild(toggleVerticalMode);
    }

    private void ArmorHudInput(){
        TextFieldWidget textFieldHudX = new TextFieldWidget(textRenderer, 270, 80, 30, 20,
                Text.literal("X").setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
        this.addDrawableChild(textFieldHudX);

        TextFieldWidget textFieldHudY = new TextFieldWidget(textRenderer, 320, 80, 30, 20,
                Text.literal("Y").setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
        this.addDrawableChild(textFieldHudY);

        ButtonWidget buttonWidgetSubmit = ButtonWidget.builder(Text.of("SAVE"), (btn) -> {
            String xValue = textFieldHudX.getText();
            String yValue = textFieldHudY.getText();
            if (xValue != null && yValue != null) {
                if (yValue.isBlank()) {
                    yValue = Integer.toString(config.armorY());
                } else if (xValue.isBlank()) {
                    xValue = Integer.toString(config.armorX());
                }
                try {
                    HudX = Integer.parseInt(xValue, 10);
                    HudY = Integer.parseInt(yValue, 10);
                    config.setArmorX();
                    config.setArmorY();
                    config.save();
                } catch (NumberFormatException e) {
                    onlyNumberToast();
                }
            }
        }).dimensions(370, 80, 30, 20).build();
        this.addDrawableChild(buttonWidgetSubmit);
    }

    public void ToggleCornerModeButton(){
        ButtonWidget ToggleCorner = ButtonWidget.builder(Text.of("Toggle Armor Huds Corner mode"), (btn) -> {
            config.toggleCornerEnabled();
            config.save();
        }).dimensions(30, 140, 120, 20).build();
        addDrawableChild(ToggleCorner);
    }

    private void MoveArmorHudToCornerButton(){
        ButtonWidget MoveArmorHudToCorner = ButtonWidget.builder(Text.of("Move Armor Hud to corner"), (btn) -> {
            if (corner == 3){
                corner -= 3;
                config.setArmorCorner();
                config.save();
            } else {
                corner++;
                config.setArmorCorner();
                config.save();
            }
            System.out.println(corner);
        }).dimensions(30, 170, 120, 20).build();
        this.addDrawableChild(MoveArmorHudToCorner);
    }

    public void GoBack(){

        ButtonWidget returnBack = ButtonWidget.builder(Text.of("<-"), (btn) -> {
            Gui.show(config);
        }).dimensions(20, 25, 35, 20).build();
        this.addDrawableChild(returnBack);
    }

    @Override
    protected void init() {
        ToggleArmorHudButton();
        ToggleVerticalMode();
        ArmorHudInput();
        MoveArmorHudToCornerButton();
        ToggleCornerModeButton();
        GoBack();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawText(this.textRenderer, "Armor Hud settings", 190, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Armor Hud location", 230, 80 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "X: ", 260, 105 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Y: ", 310, 105 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);

        context.drawText(this.textRenderer, "X: " + config.armorX(), 260, 130 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Y: " + config.armorY(), 310, 130 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);

    }

    public static void show(ArmorConfig config) {
        MinecraftClient.getInstance().setScreen(new armorGui(config));
    }
}
