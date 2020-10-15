package dev.alexnader.fallfest_leaves;

import com.google.common.collect.Sets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.minecraft.util.Identifier;

import java.util.Set;


@Config(name = "fallfest_leaves")
public class FallfestLeavesConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    private static FallfestLeavesConfig instance = null;

    public static FallfestLeavesConfig getOrLoad() {
        if (instance == null) {
            return reloadAndGet();
        } else {
            return instance;
        }
    }

    public static FallfestLeavesConfig reloadAndGet() {
        instance = AutoConfig.getConfigHolder(FallfestLeavesConfig.class).getConfig();
        return instance;
    }

    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
    private boolean colorLeaves = true;
    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
    private Set<Identifier> coloredLeaves = Sets.newHashSet(
        FallfestLeaves.id("test_leaves"),
        new Identifier("minecraft:oak_leaves"),
        new Identifier("minecraft:birch_leaves"),
        new Identifier("minecraft:dark_oak_leaves")
    );

    public boolean colorLeaves() {
        return colorLeaves;
    }

    public Set<Identifier> coloredLeaves() {
        return coloredLeaves;
    }
}
