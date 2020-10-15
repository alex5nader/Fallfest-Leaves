package dev.alexnader.fallfest.mixin;

import dev.alexnader.fallfest.Fallfest;
import dev.alexnader.fallfest.FallfestConfig;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.Set;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    @Inject(method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Ljava/util/Set;Ljava/util/Set;Lnet/minecraft/util/math/BlockBox;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At("HEAD"))
    void cacheColorAndPos(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        boolean shouldColor = FallfestConfig.getOrLoad().coloredLeaves().contains(Registry.BLOCK.getId(config.leavesProvider.getBlockState(new Random(), new BlockPos(0, 0, 0)).getBlock()));
        if (shouldColor) {
            Fallfest.currentTreeFallColor.set((int) (MathHelper.hashCode(pos) & 7));
        } else {
            Fallfest.currentTreeFallColor.set(null);
        }
    }
}
