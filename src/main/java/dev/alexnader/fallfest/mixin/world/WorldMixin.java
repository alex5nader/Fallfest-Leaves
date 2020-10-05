package dev.alexnader.fallfest.mixin.world;

import dev.alexnader.fallfest.mixinterface.ToWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(World.class)
public class WorldMixin implements ToWorld {
    @Override
    public World toWorld() {
        return (World) (Object) this;
    }
}
