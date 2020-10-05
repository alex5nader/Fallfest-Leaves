package dev.alexnader.fallfest.component;

import dev.onyxstudios.cca.api.v3.component.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BlockPosComponent implements ComponentV3, AutoSyncedComponent {
    private boolean valid = true;
    private @Nullable BlockPos value;

    public BlockPosComponent(@Nullable BlockPos value) {
        System.out.println("Created BlockPosComponent");
        this.value = value;
    }

    public @Nullable BlockPos get() {
        return value;
    }

    public void set(@Nullable BlockPos value) {
        if (valid) {
            this.value = value;
        }
    }

    public void invalidate() {
        this.valid = false;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        if (compoundTag.contains("pos")) {
            value = NbtHelper.toBlockPos(compoundTag.getCompound("pos"));
        } else {
            value = null;
        }
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        if (valid && value != null) {
            compoundTag.put("pos", NbtHelper.fromBlockPos(value));
        }
    }
}
