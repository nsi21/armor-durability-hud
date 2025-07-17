package mc.armorhudforge.hud;

import com.mojang.blaze3d.platform.InputConstants;
import mc.armorhudforge.utils.ArmorUtil;
import mc.armorhudforge.utils.HudPositionUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.util.function.Function;

import static mc.armorhudforge.utils.HudPositionUtil.*;

public class ArmorHud {
    private final int helmet = 5;
    private final int chestplate= 4;
    private final int leggings = 3;
    private final int boots = 2;

    private final int helmetTools = 3;
    private final int chestplateTools = 2;
    private final int leggingsTools = 1;
    private final int bootsTools = 0;

    //    private final String[] armorResourceLocations = {
//            "textures/item/empty_armor_slot_boots.png",
//            "textures/item/empty_armor_slot_leggings.png",
//            "textures/item/empty_armor_slot_chestplate.png",
//            "textures/item/empty_armor_slot_helmet.png"
//    };
    private final String[] armorResourceLocations = {
            "textures/gui/sprites/container/slot/boots.png",
            "textures/gui/sprites/container/slot/leggings.png",
            "textures/gui/sprites/container/slot/chestplate.png",
            "textures/gui/sprites/container/slot/helmet.png"
    };


    public static final KeyMapping Key = new KeyMapping("key.keyboard.N", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, "key.keyboard.N");

    public void renderArmorAndDamage(final GuiGraphics gui) {
        Minecraft minecraft = Minecraft.getInstance();
        new HudPositionUtil().countPos();


//        gui.renderItem(ArmorUtil.getArmor(helmet, Function.identity()), x1, y1);
//        gui.renderItem(ArmorUtil.getArmor(chestplate, Function.identity()), x2, y2);
//        gui.renderItem(ArmorUtil.getArmor(leggings, Function.identity()), x3, y3);
//        gui.renderItem(ArmorUtil.getArmor(boots, Function.identity()), x4, y4);

        gui.renderItem(ArmorUtil.getArmor(helmet, Function.identity()), x1, y1);
        gui.renderItem(ArmorUtil.getArmor(chestplate, Function.identity()), x2, y2);
        gui.renderItem(ArmorUtil.getArmor(leggings, Function.identity()), x3, y3);
        gui.renderItem(ArmorUtil.getArmor(boots, Function.identity()), x4, y4);

        if (ArmorUtil.getDamage(helmet) == 0) {
            emptyArmorRenderer(gui, helmetTools, x1, y1, 15);
        } else if (ArmorUtil.getDamage(helmet) < 100) {
            renderDurability(gui, helmet, x1, r1, y1);
        } else {
            renderDurability(gui, helmet, x1, l1, y1);
        }

        if (ArmorUtil.getDamage(chestplate) == 0) {
            emptyArmorRenderer(gui, chestplateTools, x2, y2, 15);
        } else if (ArmorUtil.getDamage(chestplate) < 100) {
//            gui.drawString(minecraft.font, String.valueOf(ArmorUtil.getDamage(2)), x2 + r1, y2 + 4, 0xFFFFFFFF, true);
            renderDurability(gui, chestplate, x2, r1, y2);
        } else {
            renderDurability(gui, chestplate, x2, l1, y2);
        }

        if (ArmorUtil.getDamage(leggings) == 0) {
            emptyArmorRenderer(gui, leggingsTools, x3, y3, 15);
        } else if (ArmorUtil.getDamage(leggings) < 100) {
            renderDurability(gui, leggings, x3, r2, y3);
        } else {
//            gui.drawString(minecraft.font, String.valueOf(ArmorUtil.getDamage(1)), x3 + l1, y3 + 4, 0xFFFFFFFF, true);
            renderDurability(gui, leggings, x3, l2, y3);

        }

        if (ArmorUtil.getDamage(boots) == 0) {
            emptyArmorRenderer(gui, bootsTools, x4, y4, 15);
        } else if (ArmorUtil.getDamage(boots) < 100) {
            renderDurability(gui, boots, x4, r2, y4);
        } else {
            renderDurability(gui, boots, x4, l2, y4);

        }
    }

    private void renderDurability(GuiGraphics gui, int armorId, int x, int textSide, int y) {
        gui.drawString(Minecraft.getInstance().font, String.valueOf(ArmorUtil.getDamage(armorId)), x + textSide, y + 4, 0xFFFFFFFF, true);
    }

    private void emptyArmorRenderer(GuiGraphics gui, int armorId, int x, int y, int size) {
        gui.blitInscribed(ResourceLocation.withDefaultNamespace(armorResourceLocations[armorId]), x, y, size, size, size, size);
    }

}
