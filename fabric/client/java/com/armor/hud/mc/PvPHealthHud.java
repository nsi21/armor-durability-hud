package com.armor.hud.mc;

import com.armor.hud.mc.Config.GetArmorDamage;
import com.armor.hud.mc.Config.GetItemDamage;
import com.armor.hud.mc.Config.ArmorConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class PvPHealthHud implements ClientModInitializer {
    private final int helmet = 5;
    private final int chestplate= 4;
    private final int leggings = 3;
    private final int boots = 2;
    private volatile int x1, x2, x3, x4, y1, y2, y3, y4, l1, l2, r1, r2;
//    private final String[] ResourceLocations = {"textures/item/empty_armor_slot_boots.png","textures/item/empty_armor_slot_leggings.png",
//            "textures/item/empty_armor_slot_chestplate.png", "textures/item/empty_armor_slot_helmet.png", "textures/item/empty_slot_sword.png",
//            "textures/item/empty_armor_slot_shield.png"};
    private final String[] ResourceLocations = {"textures/gui/sprites/container/slot/boots.png", "textures/gui/sprites/container/slot/leggings.png",
            "textures/gui/sprites/container/slot/chestplate.png", "textures/gui/sprites/container/slot/helmet.png", "textures/gui/sprites/container/slot/sword.png",
            "textures/gui/sprites/container/slot/shield.png"};

    private ItemStack ArmorSlot(int slot) {
        return MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.VALUES.get(slot));
    }

    private void renderMissingItem(DrawContext drawContext,  int slotId, int x, int y, int size){
        drawContext.drawTexture(RenderPipelines.GUI_TEXTURED,Identifier.of("minecraft", ResourceLocations[slotId]), x, y, 0, 0, size, size, size, size);
    }



//    private void renderMissingItem(DrawContext drawContext,  int slotId, int x, int y, int size){
//        drawContext.drawTexture(__ -> RenderLayer.getGuiTextured(Identifier.of("minecraft", ResourceLocations[slotId])),
//                Identifier.of("minecraft", ResourceLocations[slotId]), x, y, 0, 0, size, size, size, size);
//    }

//    private void renderMissingItem(DrawContext drawContext,  int slotId, int x, int y, int size){
//            drawContext.drawTexture(Identifier.of("minecraft", ResourceLocations[slotId]), x, y, 0, 0, size, size, size, size);
//    }

    @Override
    public void onInitializeClient() {

        MinecraftClient client = MinecraftClient.getInstance();

        HudRenderCallback.EVENT.register(((drawContext, tickCounter) -> {
            assert client.player != null;
            try {
                int getScaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth() - 15;

                int getScaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight() - 15;
                int x = ArmorConfig.load().armorX();
                int y = ArmorConfig.load().armorY();
                int tC = 15;
                x1 = x;
                x2 = x;
                x3 = x;
                x4 = x;

                y1 = y;
                y2 = y + 13;
                y3 = y + 28;
                y4 = y + 41;

                r1 = -14;
                r2 = -14;
                l1 = -20;
                l2 = -20;

                if (ArmorConfig.load().isVerticalModeOn()) {
//                    Helmet
                    x1 = getScaledWidth / 2 - 130;
                    y1 = getScaledHeight - 50;

//                    chestplate
                    x2 = getScaledWidth / 2 - 130;
                    y2 = getScaledHeight - 30;
//                    leggings
                    x3 = getScaledWidth / 2 + 130;
                    y3 = getScaledHeight - 50;
//                    boots
                    x4 = getScaledWidth / 2 + 130;
                    y4 = getScaledHeight - 30;

                    l1 = 20;
                    r1 = 21;

                }
                if (ArmorConfig.load().isCornerModeOn()) {
                    switch (ArmorConfig.load().armorCorner()) {
                        case 0:
//                        Left upper corner
                            x1 = 10;
                            x2 = 10;
                            x3 = 10;
                            x4 = 10;

                            y1 = 10;
                            y2 = 10 + 13;
                            y3 = 10 + 28;
                            y4 = 10 + 41;

                            l1 = 20;
                            l2 = 20;
                            r1 = 20;
                            r2 = 20;

                            break;
                        case 1:
//                        Right upper corner
                            int scw = getScaledWidth - 10;
                            x1 = scw;
                            x2 = scw;
                            x3 = scw;
                            x4 = scw;

                            y1 = 10;
                            y2 = 10 + 13;
                            y3 = 10 + 28;
                            y4 = 10 + 41;

                            r1 = -14;
                            r2 = -14;
                            l1 = -20;
                            l2 = -20;
                            break;
                        case 2:
                            x1 = 10;
                            x2 = 10;
                            x3 = 10;
                            x4 = 10;

                            y1 = getScaledHeight - 51;
                            y2 = getScaledHeight - 38;
                            y3 = getScaledHeight - 23;
                            y4 = getScaledHeight - 10;

                            l1 = 20;
                            l2 = 20;
                            r1 = 20;
                            r2 = 20;
                            break;
                        case 3:
                            x1 = getScaledWidth - 10;
                            x2 = getScaledWidth - 10;
                            x3 = getScaledWidth - 10;
                            x4 = getScaledWidth - 10;

                            y1 = getScaledHeight - 51;
                            y2 = getScaledHeight - 38;
                            y3 = getScaledHeight - 23;
                            y4 = getScaledHeight - 10;

                            r1 = -14;
                            r2 = -14;
                            l1 = -20;
                            l2 = -20;
                            break;
                    }
                }

                TextRenderer ct = client.textRenderer;

                if (ArmorConfig.load().isHudOn()) {
//                    -- armor hud (down)

                    drawContext.drawItem(ArmorSlot(helmet), x1, y1);
                    if (GetArmorDamage.helmetDamageLeft == 0) {
                        renderMissingItem(drawContext, 3, x1, y1,tC);
                    } else if (GetArmorDamage.helmetDamageLeft < 100) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.helmetDamageLeft), x1 + r1, y1 + 4, 0xFFFFFFFF, true);

                    } else if (GetArmorDamage.helmetDamageLeft > 99) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.helmetDamageLeft), x1 + l1, y1 + 4, 0xFFFFFFFF, true);
                    }

                    drawContext.drawItem(ArmorSlot(chestplate), x2, y2);
                    if (GetArmorDamage.chestplateDamageLeft == 0) {
                        renderMissingItem(drawContext, 2, x2, y2,tC);
                    } else if (GetArmorDamage.chestplateDamageLeft < 100) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.chestplateDamageLeft), x2 + r1, y2 + 5, 0xFFFFFFFF, true);

                    } else if (GetArmorDamage.chestplateDamageLeft > 99) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.chestplateDamageLeft), x2 + l1, y2 + 5, 0xFFFFFFFF, true);
                    }
                    drawContext.drawItem(ArmorSlot(leggings), x3, y3);
                    if (GetArmorDamage.leggingsDamageLeft == 0) {
                        renderMissingItem(drawContext, 1, x3, y3,tC);
                    } else if (GetArmorDamage.leggingsDamageLeft < 100) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.leggingsDamageLeft), x3 + r2, y3 + 4, 0xFFFFFFFF, true);

                    } else if (GetArmorDamage.leggingsDamageLeft > 99) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.leggingsDamageLeft), x3 + l2, y3 + 4, 0xFFFFFFFF, true);
                    }

                    drawContext.drawItem(ArmorSlot(boots), x4, y4);
                    if (GetArmorDamage.bootsDamageLeft == 0) {
                        renderMissingItem(drawContext, 0, x4, y4, tC);
                    } else if (GetArmorDamage.bootsDamageLeft < 100) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.bootsDamageLeft), x4 + r2, y4 + 5, 0xFFFFFFFF, true);

                    } else if (GetArmorDamage.bootsDamageLeft > 99) {
                        drawContext.drawText(ct, String.valueOf(GetArmorDamage.bootsDamageLeft), x4 + l2, y4 + 5, 0xFFFFFFFF, true);
                    }
                }


//                    -- Inventory hud (down) --
                if (ArmorConfig.load().isInvHudOn()) {
                    String count = String.valueOf(client.player.getInventory().count(client.player.getMainHandStack().getItem()));
                    String count_off_hand = String.valueOf(client.player.getInventory().count(client.player.getOffHandStack().getItem()));
                    int white = 0xFFFFFFFF;

                    drawContext.drawItem(client.player.getMainHandStack(), x - 50, y);
                    drawContext.drawItem(client.player.getOffHandStack(), x - 50, y + 28);

                    if (GetItemDamage.currentItem > 999) {
//                        if tool has more than 3 numbers of health it will print this
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.currentItem), x - 80, y + 4, white, true);
                    } else if (GetItemDamage.currentItem < 1000 && GetItemDamage.currentItem > 99) {
//                      When tool has less than 1k health and more than 99 this will execute
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.currentItem), x - 74, y + 4, white, true);
                    } else if (GetItemDamage.currentItem < 100 && GetItemDamage.currentItem > 9) {
//                        If tool has less than 100 health and more than 0 this will execute
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.currentItem), x - 67, y + 4, white, true);

                    } else if (GetItemDamage.currentItem < 10 && GetItemDamage.currentItem > 0) {
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.currentItem), x - 62, y + 4, white, true);

                    } else if (GetItemDamage.currentItem == 0 && client.player.getMainHandStack().isEmpty()) {
                        renderMissingItem(drawContext, 4, x - 50, y,tC);
                    } else if (GetItemDamage.currentItem == 0 && client.player.getMainHandStack().getCount() > 9) {
                        drawContext.drawText(ct, count, x - 67, y + 4, white, true);

                    } else if (GetItemDamage.currentItem == 0 && client.player.getMainHandStack().getCount() < 10) {
                        drawContext.drawText(ct, count, x - 62, y + 4, white, true);
                    }


//                    -- Offhand items --


                    if (GetItemDamage.SecondHandItem > 999) {
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.SecondHandItem), x - 80, y + 32, white, true);

                    } else if (GetItemDamage.SecondHandItem < 999 && GetItemDamage.SecondHandItem > 99) {
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.SecondHandItem), x - 74, y + 32, white, true);

                    } else if (GetItemDamage.SecondHandItem < 100 && GetItemDamage.SecondHandItem > 9) {
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.SecondHandItem), x - 67, y + 32, white, true);

                    } else if (GetItemDamage.SecondHandItem < 10 && GetItemDamage.SecondHandItem > 0) {
                        drawContext.drawText(ct, String.valueOf(GetItemDamage.SecondHandItem), x - 62, y + 32, white, true);

                    } else if (GetItemDamage.SecondHandItem == 0 && client.player.getOffHandStack().isEmpty()) {
                        renderMissingItem(drawContext, 5, x - 50, y + 28,tC);
                    } else if (GetItemDamage.SecondHandItem == 0 && client.player.getOffHandStack().getCount() > 9) {
                        drawContext.drawText(ct, count_off_hand, x - 67, y + 32, white, true);
                    } else if (GetItemDamage.SecondHandItem == 0 && client.player.getOffHandStack().getCount() < 10) {
                        drawContext.drawText(ct, count_off_hand, x - 62, y + 32, white, true);
                    }
                }


            } catch (Exception e) {
            }
        }));
    }


}
