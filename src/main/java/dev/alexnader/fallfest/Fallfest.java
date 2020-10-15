package dev.alexnader.fallfest;

import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;

public class Fallfest {
    public static final ThreadLocal<String> latestBlockPath = new ThreadLocal<>();

    public static boolean currentBlockShouldHaveColorPropery() {
        String path = Fallfest.latestBlockPath.get();
        if (path == null) {
            return false;
        }
        FallfestConfig config = FallfestConfig.getOrLoad();
        return config.colorLeaves() && config.coloredLeaves().contains(new Identifier(path));
    }

    public static final ThreadLocal<Integer> currentTreeFallColor = new ThreadLocal<>();

    public static final String NAMESPACE = "fallfest";

    public static Identifier id(String path) {
        return new Identifier(NAMESPACE, path);
    }

    public static final IntProperty FALL_COLOR = IntProperty.of("fall_color", 0, 7);
}
