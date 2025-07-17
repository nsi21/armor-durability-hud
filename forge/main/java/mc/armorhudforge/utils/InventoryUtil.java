package mc.armorhudforge.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {

    public static ItemStack getMainHandItem(){
        return Minecraft.getInstance().player.getMainHandItem();
    }

    public static ItemStack getOffHandItem(){
        return Minecraft.getInstance().player.getOffhandItem();
    }

    public static int getMainHandItemDamage(){
        return getMainHandItem().getMaxDamage() -
                getMainHandItem().getDamageValue();
    }

    public static int getOffHandItemDamage(){
        return getOffHandItem().getMaxDamage() -
                getOffHandItem().getDamageValue();
    }

    public static int getMainHandItemCount(){
        return Minecraft.getInstance().player.getInventory().countItem(Minecraft.getInstance().player.getMainHandItem().getItem());
    }

    public static int getOffHandItemCount(){
        return Minecraft.getInstance().player.getInventory().countItem(Minecraft.getInstance().player.getOffhandItem().getItem());
    }

}
