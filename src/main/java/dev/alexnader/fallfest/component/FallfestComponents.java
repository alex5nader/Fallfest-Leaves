package dev.alexnader.fallfest.component;

import com.google.common.collect.Maps;
import dev.alexnader.cca_block_storage.api.BlockStoredComponentInitializer;
import dev.alexnader.fallfest.Fallfest;
import dev.alexnader.fallfest.FallfestConfig;
import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage")
public class FallfestComponents implements BlockComponentInitializer, BlockStoredComponentInitializer {
//    public static final ComponentKey<BlockComponentStorageComponent> BLOCK_COMPONENT_STORAGE =
//            ComponentRegistryV3.INSTANCE.getOrCreate(Fallfest.id("block_component_storage"), BlockComponentStorageComponent.class);

    public static final ComponentKey<BlockPosComponent> COLOR_POS =
            ComponentRegistryV3.INSTANCE.getOrCreate(Fallfest.id("color_pos"), BlockPosComponent.class);

    @Override
    public Map<ComponentKey<?>, Function<BlockPos, ? extends Component>> blockStoredComponents() {
        Map<ComponentKey<?>, Function<BlockPos, ? extends Component>> components = new HashMap<>();
        components.put(COLOR_POS, BlockPosComponent::new);
        return components;
    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        for (Identifier id : FallfestConfig.getOrLoad().coloredLeaves()) {
            registry.registerFor(id, COLOR_POS, (state, world, pos, side) -> new BlockPosComponent(null));
        }
    }

//    @Override
//    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
//        registry.register(BLOCK_COMPONENT_STORAGE, __ -> new BlockComponentStorageComponent());
//    }
//
//    public static <C extends Component> C getBlockComponent(ComponentKey<C> key, World world, BlockPos pos) {
//        return FallfestComponents.BLOCK_COMPONENT_STORAGE.get(world).get(key).get(pos);
//    }
//
//    public static <C extends Component> ComponentProvider getBlockComponentProvider(ComponentKey<C> key, World world, BlockPos pos) {
//        return FallfestComponents.BLOCK_COMPONENT_STORAGE.get(world).get(key)
//    }
}
