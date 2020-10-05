package dev.alexnader.fallfest.mixin.world;

import dev.alexnader.fallfest.mixinterface.ToWorld;
import net.minecraft.client.render.chunk.ChunkRendererRegion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChunkRendererRegion.class)
public class ChunkRendererRegionMixin implements ToWorld {
    @Shadow @Final protected World world;

    @Override
    public World toWorld() {
        return this.world;
    }
}
