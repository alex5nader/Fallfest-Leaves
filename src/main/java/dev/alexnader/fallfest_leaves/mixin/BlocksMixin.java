package dev.alexnader.fallfest_leaves.mixin;

import dev.alexnader.fallfest_leaves.FallfestLeaves;
import dev.alexnader.fallfest_leaves.FallfestLeavesConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @Shadow private static LeavesBlock createLeavesBlock() {
        throw new IllegalStateException("Mixin shadow implementation should not be called.");
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void registerConfig(CallbackInfo ci) {
        AutoConfig.register(FallfestLeavesConfig.class, JanksonConfigSerializer::new);
    }

    @Inject(method = "register", at = @At("HEAD"))
    private static void trackPath(String id, Block block, CallbackInfoReturnable<Block> cir) {
        FallfestLeaves.latestBlockPath.set(id);
    }

    @ModifyArg(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/Registry;register(Lnet/minecraft/util/registry/Registry;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static Object replace(Object original) {
        if (FallfestLeaves.currentBlockShouldHaveColorPropery()) {
            return createLeavesBlock();
        } else {
            return original;
        }
    }
}
