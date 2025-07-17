package mc.armorhudforge.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.io.Console;
import java.util.function.Function;

public class ArmorUtil {

//    public static ItemStack get(int armorId) {
//        return withArmor(armorId, Function.identity());
//    }

    public static <T> T getArmor(int armorId, Function<ItemStack, T> fn) {
        assert Minecraft.getInstance().player != null;
        return fn.apply(Minecraft.getInstance().player.getInventory().getEquipment().get(EquipmentSlot.VALUES.get(armorId)));
    }
    public static int getDamage(int num) {
        return getArmor(num, Function.identity()).getMaxDamage() - getArmor(num, Function.identity()).getDamageValue();
    }




}
