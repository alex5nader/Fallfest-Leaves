package dev.alexnader.fallfest;

import dev.alexnader.cca_block_storage.api.BlockStoredComponents;
import dev.alexnader.fallfest.component.BlockPosComponent;
import dev.alexnader.fallfest.component.FallfestComponents;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TestLeaves extends LeavesBlock {
    public TestLeaves(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (hand == Hand.OFF_HAND) {
            return super.onUse(state, world, pos, player, hand, hit);
        }

        System.out.println("Before setting: " + BlockStoredComponents.get(FallfestComponents.COLOR_POS, world, pos).get());

        if (player.isSneaking()) {
            if (!world.isClient) {
                BlockStoredComponents.syncAfter(FallfestComponents.COLOR_POS, world, pos, comp -> {
                    BlockPos storedPos = comp.get();
                    if (storedPos != null) {
                        comp.set(storedPos.up());
                    }
                });
                System.out.println("Server after setting: " + BlockStoredComponents.get(FallfestComponents.COLOR_POS, world, pos).get());
            } else {
                System.out.println("Client after setting: " + BlockStoredComponents.get(FallfestComponents.COLOR_POS, world, pos).get());
            }
        }

        System.out.println();

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        System.out.println(BlockStoredComponents.get(FallfestComponents.COLOR_POS, world, pos).get());
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        BlockStoredComponents.get(FallfestComponents.COLOR_POS, world, pos).invalidate();

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
    }
}
