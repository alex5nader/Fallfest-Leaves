package dev.alexnader.fallfest_leaves.mixin;

import dev.alexnader.fallfest_leaves.client.FallColorsProvider;
import dev.alexnader.fallfest_leaves.FallfestLeavesConfig;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Mixin(BlockColors.class)
public class BlockColorsMixin {
    @ModifyArg(
            method = "create",
            index = 1,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/color/block/BlockColors;registerColorProvider(Lnet/minecraft/client/color/block/BlockColorProvider;[Lnet/minecraft/block/Block;)V",
                    ordinal = 4
            )
    )
    private static Block[] hijackBlocks(Block[] blocks) {
        List<Block> noRecolors = new ArrayList<>(Arrays.asList(blocks));
        Set<Identifier> recolorIds = FallfestLeavesConfig.getOrLoad().coloredLeaves();
        noRecolors.removeIf(b -> recolorIds.contains(Registry.BLOCK.getId(b)));
        return noRecolors.toArray(new Block[0]);
    }

    @Inject(method = "create", at = @At("RETURN"))
    private static void addFallColors(CallbackInfoReturnable<BlockColors> cir) {
        Block[] recolors = FallfestLeavesConfig.getOrLoad().coloredLeaves().stream().map(Registry.BLOCK::get).toArray(Block[]::new);
        cir.getReturnValue().registerColorProvider(new FallColorsProvider(), recolors);
    }
}
