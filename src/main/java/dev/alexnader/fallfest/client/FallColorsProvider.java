package dev.alexnader.fallfest.client;

import dev.alexnader.fallfest.Fallfest;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public class FallColorsProvider implements BlockColorProvider {
    final int[] colors = {
        0x8E1803,
        0xA02604,
        0xB13604,
        0xC34905,
        0xD45E05,
        0xDF7F12,
        0xE8A024,
        0xF1C136,
    };

    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (state.contains(Fallfest.FALL_COLOR)) {
            return colors[state.get(Fallfest.FALL_COLOR)];
        } else {
            return -1;
        }
    }
}
