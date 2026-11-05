package dev.grassseamfix;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.util.ARGB;

import java.util.Map;

public final class SeamFiller {

    public static final Map<String, String> PAIRS = Map.of(
            "minecraft:block/grass_block_side", "minecraft:block/grass_block_side_overlay"
    );

    private SeamFiller() {
    }

    public static void fill(NativeImage base, NativeImage overlay) {
        if (base.getWidth() != overlay.getWidth() || base.getHeight() != overlay.getHeight()) return;

        int width = base.getWidth();
        int height = base.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (isClear(overlay, x, y)) continue;

                int below = firstClearBelow(overlay, x, y, height);
                if (below < 0) continue;

                base.setPixel(x, y, base.getPixel(x, below));
            }
        }
    }

    private static int firstClearBelow(NativeImage overlay, int x, int fromY, int height) {
        for (int y = fromY + 1; y < height; y++) {
            if (isClear(overlay, x, y)) return y;
        }
        return -1;
    }

    private static boolean isClear(NativeImage image, int x, int y) {
        return ARGB.alpha(image.getPixel(x, y)) == 0;
    }
}
