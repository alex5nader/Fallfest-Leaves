package dev.alexnader.fallfest_leaves;

import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;

public class FallfestLeaves {
    public static final ThreadLocal<String> latestBlockPath = new ThreadLocal<>();

    public static boolean currentBlockShouldHaveColorPropery() {
        String path = FallfestLeaves.latestBlockPath.get();
        if (path == null) {
            return false;
        }
        FallfestLeavesConfig config = FallfestLeavesConfig.getOrLoad();
        return config.colorLeaves() && config.coloredLeaves().contains(new Identifier(path));
    }

    public static final ThreadLocal<Integer> currentTreeFallColor = new ThreadLocal<>();

    public static final String NAMESPACE = "fallfest_leaves";

    public static Identifier id(String path) {
        return new Identifier(NAMESPACE, path);
    }

    public static final IntProperty FALL_COLOR = IntProperty.of("fall_color", 0, 7);
}
