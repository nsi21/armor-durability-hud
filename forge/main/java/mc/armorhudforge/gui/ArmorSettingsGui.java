package mc.armorhudforge.gui;

import mc.armorhudforge.ArmorConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ArmorSettingsGui extends Screen {
    public static volatile int hudX, hudY;
    private final ArmorConfig config;

    protected ArmorSettingsGui(ArmorConfig config) {
        super(Component.empty());
        this.config = config;
    }

    private void goBack() {
        Button goBack = Button.builder(Component.nullToEmpty("<-"), (btn) -> {
            close();
        }).pos(20, 25).size(35, 20).build();
        this.addRenderableWidget(goBack);
    }

    private void toggleArmorHud() {
        Button toggleArmorHudButton = Button.builder(Component.nullToEmpty("Toggle armor hud"), (btn) -> {
            config.toggleHud();
            config.save();
        }).pos(30, 80).size(120, 20).build();
        this.addRenderableWidget(toggleArmorHudButton);
    }

    //    private void openDragUI() {
//        Button openDragUIButton = Button.builder(Component.nullToEmpty("DragUI"), (btn) -> {
//            DragUI.open(Component.empty());
//        }).pos(30, 110).size(120, 20).build();
//        this.addRenderableWidget(openDragUIButton);
//    }
    private void isVerticalModeOn() {
        Button isVerticalModeOn = Button.builder(Component.nullToEmpty("Toggle Vertical mode"), (btn) -> {
            config.toggleVerticalHud();
            config.save();
        }).pos(30, 110).size(120, 20).build();
        this.addRenderableWidget(isVerticalModeOn);
    }

        private void armorHudInput () {
            EditBox inputX = new EditBox(Minecraft.getInstance().font, 270, 80, 30, 20, Component.empty());
            this.addRenderableWidget(inputX);

            EditBox inputY = new EditBox(Minecraft.getInstance().font, 320, 80, 30, 20, Component.empty());
            this.addRenderableWidget(inputY);

            Button saveInputValues = Button.builder(Component.nullToEmpty("Save"), (btn) -> {
                String xValue = inputX.getValue();
                String yValue = inputY.getValue();

                if (xValue != null && yValue != null) {
                    if (yValue.isBlank()) {
                        yValue = Integer.toString(config.getArmorY());
                    } else if (xValue.isBlank()) {
                        xValue = Integer.toString(config.getArmorY());
                    }
                    try {
                        hudX = Integer.parseInt(xValue, 10);
                        hudY = Integer.parseInt(yValue, 10);
                        config.setArmorX();
                        config.setArmorY();
                        config.save();
                    } catch (NumberFormatException e) {
                        onlyNumberToast();
                    }
                    System.out.println(config.getArmorX());
                    System.out.println(config.getArmorY());

                }

            }).pos(370, 80).size(30, 20).build();
            this.addRenderableWidget(saveInputValues);
        }

        @Override
        protected void init () {
            goBack();
            toggleArmorHud();
            armorHudInput();
            isVerticalModeOn();
        }

        public void render (GuiGraphics gui,int mouseX, int mouseY, float partialTick){
            Font font = Minecraft.getInstance().font;
            super.render(gui, mouseX, mouseY, partialTick);

            gui.drawString(font, "Armor Hud settings", 190, 40 - font.lineHeight - 10, 0xFFFFFFFF, true);
            gui.drawString(font, "Armor Hud location", 230, 80 - font.lineHeight - 10, 0xFFFFFFFF, true);

            gui.drawString(font, "X: ", 260, 105 - font.lineHeight - 10, 0xFFFFFFFF, true);
            gui.drawString(font, "Y: ", 310, 105 - font.lineHeight - 10, 0xFFFFFFFF, true);

            gui.drawString(font, "X: " + config.getArmorX(), 260, 130 - font.lineHeight - 10, 0xFFFFFFFF, true);
            gui.drawString(font, "Y: " + config.getArmorY(), 310, 130 - font.lineHeight - 10, 0xFFFFFFFF, true);
        }

        public static void open (Component component){
            Minecraft.getInstance().setScreen(new ArmorSettingsGui(ArmorConfig.load()));
        }

        public static void close () {
            Minecraft.getInstance().setScreen(null);
        }


        public void onlyNumberToast () {
            Minecraft.getInstance().getToastManager().addToast(
                    SystemToast.multiline(Minecraft.getInstance(), SystemToast.SystemToastId.NARRATOR_TOGGLE,
                            Component.nullToEmpty("NOTICE!"), Component.nullToEmpty("Only Use Numbers!"))
            );
        }

//    public void onlyNumberToast() {
//        Minecraft.getInstance().getToasts().addToast(
//                SystemToast.multiline(Minecraft.getInstance(), SystemToast.SystemToastId.NARRATOR_TOGGLE,
//                        Component.nullToEmpty("NOTICE!"), Component.nullToEmpty("Only Use Numbers!"))
//        );
//    }

    }
