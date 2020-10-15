package dev.alexnader.fallfest_leaves.mixin;

import dev.alexnader.fallfest_leaves.FallfestLeaves;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;
import java.util.Set;

@Mixin(FoliagePlacer.class)
public class FoliagePlacerMixin {
    @Redirect(
            method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;Lnet/minecraft/util/math/BlockPos;ILjava/util/Set;IZLnet/minecraft/util/math/BlockBox;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/stateprovider/BlockStateProvider;getBlockState(Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;")
    )
    BlockState preserveRandomState(BlockStateProvider blockStateProvider, Random random, BlockPos pos) {
        return null;
    }

    @Redirect(
            method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;Lnet/minecraft/util/math/BlockPos;ILjava/util/Set;IZLnet/minecraft/util/math/BlockBox;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/ModifiableTestableWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z")
    )
    boolean dontSetLeaves(ModifiableTestableWorld modifiableTestableWorld, BlockPos pos, BlockState state, int flags) {
        return false;
    }

    @Inject(
            method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;Lnet/minecraft/util/math/BlockPos;ILjava/util/Set;IZLnet/minecraft/util/math/BlockBox;)V",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/ModifiableTestableWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    void setLeaves(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, BlockPos treePos, int baseHeight, Set<BlockPos> leaves, int offset, boolean giantTrunk, BlockBox box, CallbackInfo ci, int i, BlockPos.Mutable leafPos) {
        BlockState leavesState = config.leavesProvider.getBlockState(random, leafPos);

        Integer fallColor = FallfestLeaves.currentTreeFallColor.get();

        if (fallColor != null) {
            world.setBlockState(leafPos, leavesState.with(FallfestLeaves.FALL_COLOR, fallColor), 19);
        } else {
            world.setBlockState(leafPos, leavesState, 19);
        }
    }
}
