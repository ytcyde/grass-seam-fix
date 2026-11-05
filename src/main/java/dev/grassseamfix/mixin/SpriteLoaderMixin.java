package dev.grassseamfix.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import dev.grassseamfix.SeamFiller;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.SpriteLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Mixin(SpriteLoader.class)
public abstract class SpriteLoaderMixin {

    @Inject(method = "stitch", at = @At("HEAD"))
    private void grassseamfix$fillUnderOverlays(List<SpriteContents> sprites, int maxMipmapLevels, Executor executor, CallbackInfoReturnable<?> cir) {
        Map<String, NativeImage> images = new HashMap<>();
        for (SpriteContents sprite : sprites) {
            images.put(sprite.name().toString(), ((SpriteContentsAccessor) sprite).grassseamfix$getOriginalImage());
        }

        for (Map.Entry<String, String> pair : SeamFiller.PAIRS.entrySet()) {
            NativeImage base = images.get(pair.getKey());
            NativeImage overlay = images.get(pair.getValue());
            if (base == null || overlay == null) continue;

            SeamFiller.fill(base, overlay);
        }
    }
}
