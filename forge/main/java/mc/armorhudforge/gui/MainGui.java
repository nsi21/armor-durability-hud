package mc.armorhudforge.gui;

import mc.armorhudforge.ArmorConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MainGui extends Screen {
    private final ArmorConfig config;

    protected MainGui(ArmorConfig config) {
        super(Component.empty());
        this.config = config;
    }

    private void goBack(){
        Button goBack = Button.builder(Component.nullToEmpty("<-"), (btn) ->{
        close();
        }).pos(20,25).size(35,20).build();
        this.addRenderableWidget(goBack);
    }

    private void OpenArmorSettingsGui(){
        Button armorHudSettings = Button.builder(Component.nullToEmpty("Armor HUD Settings"), (btn) -> {
            ArmorSettingsGui.open(Component.empty());
        }).pos(30,80).size(120,20).build();
        this.addRenderableWidget(armorHudSettings);
    }
    private void toggleInventoryHud(){
        Button buttonWidgetToggleInventory = Button.builder(Component.nullToEmpty(("Toggle Inventory HUD")), (btn) -> {
            config.toggleInvHud();
            config.save();
        }).pos(30, 110).size( 120, 20).build();
        this.addRenderableWidget(buttonWidgetToggleInventory);
    }

    @Override
    protected void init(){
        OpenArmorSettingsGui();
        goBack();
        toggleInventoryHud();
    }

//    public static void render(GuiGraphics gui){
//
//    }

    public static void open(Component component) {
        Minecraft.getInstance().setScreen(new MainGui(ArmorConfig.load()));
    }

    public static void close(){
        Minecraft.getInstance().setScreen(null);
    }

}