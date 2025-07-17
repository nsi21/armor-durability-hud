package mc.armorhudforge.events;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import mc.armorhudforge.ArmorConfig;
import mc.armorhudforge.hud.ArmorHud;
import mc.armorhudforge.hud.InventoryHud;
import mc.armorhudforge.utils.WriteToMissingFile;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jline.terminal.MouseEvent;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;


import static mc.armorhudforge.gui.DragUI.focusableTextWidget;
//import static mc.armorhudforge.gui.DragUI.moveButton;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Armorhudforge.MODID)
public class Armorhudforge {

    public static final String MODID = "armorhudforge";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static ArmorHud hud = new ArmorHud();

    public Armorhudforge() {
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @Mod.EventBusSubscriber(modid = Armorhudforge.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void hudRenderer(CustomizeGuiOverlayEvent event) {
            ArmorConfig config = ArmorConfig.load();
            if (config.isHudOn()) {
                hud.renderArmorAndDamage(event.getGuiGraphics());
            }
            if (config.isInvHudOn()){
                InventoryHud.renderInventoryItemsAndDamage(event.getGuiGraphics());
            }
            try {
                 KeyMapping Keyd = new KeyMapping("key.keyboard.N", KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_LEFT, "key.keyboard.N");

                if (focusableTextWidget.isHovered()){
                    System.out.println("Now it works");
                }
            } catch (Exception ignored){}
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            WriteToMissingFile.writeToFile(ArmorConfig.load());
        }
    }
}
