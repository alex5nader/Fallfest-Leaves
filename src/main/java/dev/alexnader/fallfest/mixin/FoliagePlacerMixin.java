package dev.alexnader.fallfest.mixin;

import dev.alexnader.fallfest.component.BlockPosComponent;
import dev.alexnader.fallfest.component.FallfestComponents;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;
import java.util.Set;

@Mixin(FoliagePlacer.class)
public class FoliagePlacerMixin {
    @Inject(
            method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;Lnet/minecraft/util/math/BlockPos;ILjava/util/Set;IZLnet/minecraft/util/math/BlockBox;)V",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/ModifiableTestableWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    @SuppressWarnings("UnstableApiUsage")
    void addComponent(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, BlockPos treePos, int baseHeight, Set<BlockPos> leaves, int offset, boolean giantTrunk, BlockBox box, CallbackInfo ci, int i, BlockPos.Mutable leafPos) {
        if (world instanceof BlockView) {
            BlockView bv = (BlockView) world;
            BlockPosComponent comp = BlockComponents.get(FallfestComponents.COLOR_POS, bv, leafPos);
            if (comp != null) {
                System.out.println("FPM: setting pos for " + leafPos + " to " + treePos);
                comp.set(treePos.toImmutable());
            } else {
                System.out.println("FPM: shit no component");
            }
        } else {
            System.out.println("FPM: shit not a blockview");
        }
    }

    @Inject(
            method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;Lnet/minecraft/util/math/BlockPos;ILjava/util/Set;IZLnet/minecraft/util/math/BlockBox;)V",
            at = @At("HEAD")
    )
    void test(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, BlockPos pos, int baseHeight, Set<BlockPos> leaves, int offset, boolean giantTrunk, BlockBox box, CallbackInfo ci) {
        System.out.println();
        System.out.println("FPM: placing tree at " + pos);
    }
}
