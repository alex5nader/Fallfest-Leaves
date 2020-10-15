package dev.alexnader.fallfest;

import com.google.common.collect.Sets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.minecraft.util.Identifier;

import java.util.Set;


@Config(name = "fallfest")
public class FallfestConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    private static FallfestConfig instance = null;

    public static FallfestConfig getOrLoad() {
        if (instance == null) {
            return reloadAndGet();
        } else {
            return instance;
        }
    }

    public static FallfestConfig reloadAndGet() {
        instance = AutoConfig.getConfigHolder(FallfestConfig.class).getConfig();
        return instance;
    }

    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
    private boolean colorLeaves = true;
    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
    private Set<Identifier> coloredLeaves = Sets.newHashSet(
        Fallfest.id("test_leaves"),
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
