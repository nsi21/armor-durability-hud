package mc.armorhudforge.hud;

import mc.armorhudforge.ArmorConfig;
import mc.armorhudforge.utils.HudPositionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import static mc.armorhudforge.utils.InventoryUtil.*;

public class InventoryHud {
//                private static final String[] itemResourceLocations = {
//                "textures/item/empty_slot_sword.png",
//                "textures/item/empty_armor_slot_shield.png"
//                };
    private static final String[] itemResourceLocations = {
            "textures/gui/sprites/container/slot/sword.png",
            "textures/gui/sprites/container/slot/shield.png"
    };

    public static void renderInventoryItemsAndDamage(final GuiGraphics gui) {
        new HudPositionUtil().countPos();
        int x = new HudPositionUtil().getX();
        int y = new HudPositionUtil().getY();
        try {
            if (ArmorConfig.load().isInvHudOn()) {
                int white = 0xFFFFFFFF;
                Font font = Minecraft.getInstance().font;
                gui.renderItem(getMainHandItem(), x - 50, y);
                gui.renderItem(getOffHandItem(), x - 50, y + 28);

                if (getMainHandItemDamage() > 999) {
//                        if tool has more than 3 numbers of health it will print this
                    gui.drawString(font, String.valueOf(getMainHandItemDamage()), x - 80, y + 4, white, true);

                } else if (getMainHandItemDamage() < 1000 && getMainHandItemDamage() > 99) {
//                      When tool has less than 1k health and more than 99 this will execute
                    gui.drawString(font, String.valueOf(getMainHandItemDamage()), x - 74, y + 4, white, true);

                } else if (getMainHandItemDamage() < 100 && getMainHandItemDamage() > 9) {
//                        If tool has less than 100 health and more than 0 this will execute
                    gui.drawString(font, String.valueOf(getMainHandItemDamage()), x - 67, y + 4, white, true);

                } else if (getMainHandItemDamage() < 10 && getMainHandItemDamage() > 0) {
                    gui.drawString(font, String.valueOf(getMainHandItemDamage()), x - 62, y + 4, white, true);

                } else if (Minecraft.getInstance().player.getMainHandItem().isEmpty()) {
                    emptyItemRenderer(gui, 0, x - 50, y, 15);

                } else if (getMainHandItemDamage() == 0 && getMainHandItemCount() > 9) {
                    gui.drawString(font, String.valueOf(getMainHandItemCount()), x - 67, y + 4, white, true);

                } else if (getMainHandItemDamage() == 0 && getMainHandItemCount() < 10) {
                    gui.drawString(font, String.valueOf(getMainHandItemCount()), x - 62, y + 4, white, true);

                }

//                    -- Offhand items --

                if (getOffHandItemDamage() > 999) {
                    gui.drawString(font, String.valueOf(getOffHandItemDamage()), x - 80, y + 32, white, true);

                } else if (getOffHandItemDamage() < 999 && getOffHandItemDamage() > 99) {
                    gui.drawString(font, String.valueOf(getOffHandItemDamage()), x - 74, y + 32, white, true);

                } else if (getOffHandItemDamage() < 100 && getOffHandItemDamage() > 9) {
                    gui.drawString(font, String.valueOf(getOffHandItemDamage()), x - 67, y + 32, white, true);

                } else if (getOffHandItemDamage() < 10 && getOffHandItemDamage() > 0) {
                    gui.drawString(font, String.valueOf(getOffHandItemDamage()), x - 62, y + 32, white, true);

                } else if (Minecraft.getInstance().player.getOffhandItem().isEmpty()) {
                    emptyItemRenderer(gui, 1, x - 50, y + 28, 15);

                } else if (getOffHandItemDamage() == 0 && getOffHandItemDamage() > 9) {
                    gui.drawString(font, String.valueOf(getMainHandItemCount()), x - 67, y + 32, white, true);

                } else if (getOffHandItemDamage() == 0 && getOffHandItemDamage() < 10) {
                    gui.drawString(font, String.valueOf(getOffHandItemCount()), x - 62, y + 32, white, true);
                }
            }


        } catch (Exception e) {
        }
    }

    private static void emptyItemRenderer(GuiGraphics gui, int slotId, int x, int y, int size) {
        gui.blitInscribed(ResourceLocation.withDefaultNamespace(itemResourceLocations[slotId]), x, y, size, size, size, size);
    }

}
