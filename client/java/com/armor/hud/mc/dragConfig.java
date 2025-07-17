//package com.armor.hud.mc;
//
//import com.sun.jna.platform.unix.X11;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.Mouse;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.widget.ButtonWidget;
//import net.minecraft.client.gui.widget.TextFieldWidget;
//import net.minecraft.client.gui.widget.ToggleButtonWidget;
//import net.minecraft.client.toast.SystemToast;
//import net.minecraft.text.Style;
//import net.minecraft.text.Text;
//import net.minecraft.util.Formatting;
//
//import java.awt.*;
//
//public class dragConfig extends Screen {
//    public static volatile int HudX, HudY;
//    private volatile int rx, ry = 100;
//    private volatile int  y, x;
//    public void onlyNumberToast() {
//        assert this.client != null;
//        this.client.getToastManager().add(
//                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("NOTICE!"), Text.of("Only Use Numbers!"))
//        );
//    }
//
//    private final armorConfig config;
//
//    public dragConfig(
//            armorConfig config) {
//        super(Text.empty());
//        this.config = config;
//    }
//
//    private void getmouse() {
//        int ef = 20;
//        while (true) {
//            ef = -1;
//
//            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
//            Point b = pointerInfo.getLocation();
//            x = (int) b.getX();
//            y = (int) b.getY();
//            new Mouse()
//        }
//    }
//    @Override
//    protected void init() {
//
//        ButtonWidget ToggleArmorHud = ButtonWidget.builder(Text.of("Toggle armor hud"), (btn) -> {
//            getmouse();
//        }).dimensions(rx, ry, 120, 20).build();
//        this.addDrawableChild(ToggleArmorHud);
//        if (ToggleArmorHud.isFocused()) {
//            rx = x;
//            ry = y;
//        }
//        System.out.println(y + " "+x);
//
//
//    }
//
//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        super.render(context, mouseX, mouseY, delta);
//
//        context.drawText(this.textRenderer, "Armor Hud settings", x, y, 0xFFFFFFFF, true);
//
//    }
//
//    public static void show(armorConfig config) {
//        MinecraftClient.getInstance().setScreen(new dragConfig(config));
//    }
//}
//
//
//

