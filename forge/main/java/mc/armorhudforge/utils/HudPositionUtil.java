package mc.armorhudforge.utils;

import com.mojang.blaze3d.platform.Window;
import mc.armorhudforge.ArmorConfig;
import net.minecraft.client.Minecraft;

public class HudPositionUtil {
    public static volatile int x1, x2, x3, x4, y1, y2, y3, y4, r1, r2, l1, l2;
    private static int x, y;

    public int getX() {
        return x;
    }

    public void setX(int num) {
        x = num;
    }

    public int getY() {
        return y;
    }

    public void setY(int num) {
        y = num;
    }

    Minecraft minecraft = Minecraft.getInstance();
    Window window = minecraft.getWindow();


    public void countPos() {
        this.setX(ArmorConfig.load().getArmorX());
        this.setY(ArmorConfig.load().getArmorY());

        int screenWidth = window.getGuiScaledWidth() - 15;
        int screenHeight = window.getGuiScaledHeight() - 15;
//        x1 = screenWidth - 10;
//        x2 = screenWidth - 10;
//        x3 = screenWidth - 10;
//        x4 = screenWidth - 10;
//
//        y1 = screenHeight - 51;
//        y2 = screenHeight - 38;
//        y3 = screenHeight - 23;
//        y4 = screenHeight - 10;


        if (ArmorConfig.load().isVerticalHudOn()) {
//                    Helmet
            x1 = screenWidth / 2 - 130;
            y1 = screenHeight - 50;

//                    chestplate
            x2 = screenWidth / 2 - 130;
            y2 = screenHeight - 30;
//                    leggings
            x3 = screenWidth / 2 + 130;
            y3 = screenHeight - 50;
//                    boots
            x4 = screenWidth / 2 + 130;
            y4 = screenHeight - 30;

            r1 = 21;
            r2 = -14;
            l1 = 20;
            l2 = -20;
            this.setX(screenWidth / 2 + 250);
            this.setY(screenHeight / 2 + 80);

        } else {
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
        }

    }

}
