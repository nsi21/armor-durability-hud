package mc.armorhudforge.gui;

import mc.armorhudforge.ArmorConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.concurrent.TimeUnit;

public class DragUI extends Screen {
    private final ArmorConfig config;
    private static volatile boolean isTrue = true;
    public static volatile FocusableTextWidget focusableTextWidget;

    protected DragUI(ArmorConfig config) {
        super(Component.empty());
        this.config = config;
    }

//    private void goBack(){
//        Button goBack = Button.builder(Component.nullToEmpty("<-"), (btn) ->{
//        close();
//        }).pos(20,25).size(35,20).build();
//        this.addRenderableWidget(goBack);
//
//    }

//    private void drag(){
//        focusableTextWidget = new FocusableTextWidget(100,
//                Component.nullToEmpty("Armor POS"), Minecraft.getInstance().font,true, 10);
//        this.addRenderableWidget(focusableTextWidget);
//        focusableTextWidget.setX(100);
//        focusableTextWidget.setY(30);
//    }



//    @Override
//    protected void init(){
////        goBack();
//        drag();
//    }

//    public static void render(GuiGraphics gui){
//
//    }

    public static void open(Component component) {
        Minecraft.getInstance().setScreen(new DragUI(ArmorConfig.load()));
    }

    public static void close(){
        Minecraft.getInstance().setScreen(null);
    }

}