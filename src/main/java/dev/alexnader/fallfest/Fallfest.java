package dev.alexnader.fallfest;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fallfest implements ModInitializer {
    public static final String NAMESPACE = "fallfest";

    public static Identifier id(String path) {
        return new Identifier(NAMESPACE, path);
    }

    public static final Logger LOG = LogManager.getLogger("Fallfest");

    public static final Block TEST_LEAVES = new TestLeaves(
        AbstractBlock.Settings
                .of(Material.LEAVES)
                .strength(0.2F)
                .ticksRandomly()
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .allowsSpawning((__, ___, ____, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
                .suffocates((__, ___, ____) -> false)
                .blockVision((__, ___, ____) -> false)
    );

    @Override
    public void onInitialize() {
        ItemGroup group = FabricItemGroupBuilder.create(id(NAMESPACE)).build();

        Registry.register(Registry.BLOCK, id("test_leaves"), TEST_LEAVES);
        Item testLeavesItem = new BlockItem(TEST_LEAVES, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, id("test_leaves"), testLeavesItem);
    }
}
