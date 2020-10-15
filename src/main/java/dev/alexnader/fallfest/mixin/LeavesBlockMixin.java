package dev.alexnader.fallfest.mixin;

import dev.alexnader.fallfest.Fallfest;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
        throw new IllegalStateException("Mixin constructor should not run");
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    void maybeSetDefaultFallColor(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (Fallfest.currentBlockShouldHaveColorPropery()) {
            this.setDefaultState(this.getDefaultState().with(Fallfest.FALL_COLOR, 0));
        }
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    void maybeAddFallColorProperty(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        if (Fallfest.currentBlockShouldHaveColorPropery()) {
            builder.add(Fallfest.FALL_COLOR);
        }
    }
}
