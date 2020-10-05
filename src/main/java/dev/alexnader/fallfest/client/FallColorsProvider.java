package dev.alexnader.fallfest.client;

import dev.alexnader.cca_block_storage.api.BlockStoredComponents;
import dev.alexnader.fallfest.component.BlockPosComponent;
import dev.alexnader.fallfest.component.FallfestComponents;
import dev.alexnader.fallfest.mixinterface.ToWorld;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FallColorsProvider implements BlockColorProvider {
    private static final Random random = new Random();

    int[] colors = {
        0x4B0A0E,
        0x550B0D,
        0x5F0D0C,
        0x69120E,
        0x74170F,
        0x7E1D10,
        0x882411,
        0x922C13,
        0x9C3414,
        0xA63D15,
        0xB04617,
        0xBA5118,
        0xC55B19,
        0xCF671A,
        0xD9731C,
        0xe3801d,
        0xE48622,
        0xE48C26,
        0xE5922B,
        0xE59730,
        0xE69D34,
        0xE7A239,
        0xE7A73E,
        0xE8AC42,
        0xE8B147,
        0xE9B64C,
        0xE9BB50,
        0xEABF55,
        0xEBC35A,
        0xEBC85E,
        0xECCC63,
        0xECD068,
    };

    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (pos == null || world == null) {
            System.out.println("FCP: shit no pos or no world");
            return -1;
        } else {
            BlockPos colorPos = null;
//            BlockPosComponent comp = BlockStoredComponents.get(FallfestComponents.COLOR_POS, ((ToWorld) world).toWorld(), pos);
//            if (comp != null) {
//                BlockPos compPos = comp.get();
//                if (compPos != null) {
//                    System.out.println("FCP: got a stored pos yeet " + compPos);
//                    colorPos = compPos;
//                } else {
//                    System.out.println("FCP: shit no stored pos");
//                    colorPos = null;
//                }
//            } else {
//                System.out.println("FCP: shit no component");
//                colorPos = null;
//            }

            if (colorPos != null) {
                long hash = MathHelper.hashCode(colorPos);
                return colors[(int) (hash & 15)];
            } else {
                return -1;
            }
        }
    }
}
