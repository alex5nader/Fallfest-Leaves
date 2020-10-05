//package dev.alexnader.fallfest.component;
//
//import dev.onyxstudios.cca.api.v3.component.*;
//import nerdhub.cardinal.components.api.component.Component;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.util.math.BlockPos;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SuppressWarnings("UnstableApiUsage")
//public class BlockComponentStorageComponent implements ComponentV3 {
//    private final ComponentContainer.Factory<BlockPos> factory = ComponentContainer.Factory.builder(BlockPos.class)
//            .component(FallfestComponents.COLOR_POS, BlockPosComponent::new)
//            .build();
//    private final Map<BlockPos, ComponentProvider> providers = new HashMap<>();
//
//    public BlockComponentStorageComponent() {}
//
//    @Override
//    public void readFromNbt(CompoundTag tag) {
//        providers.clear();
//        for (String posStr : tag.getKeys()) {
//            BlockPos pos = BlockPos.fromLong(Long.parseLong(posStr));
//            ComponentContainer container = factory.createContainer(pos);
//            container.fromTag(tag.getCompound(posStr));
//            providers.put(pos, new StorageComponentProvider(container));
//        }
//    }
//
//    @Override
//    public void writeToNbt(CompoundTag tag) {
//        for (Map.Entry<BlockPos, ComponentProvider> entry : providers.entrySet()) {
//            tag.put(Long.toString(entry.getKey().asLong()), entry.getValue().getComponentContainer().toTag(new CompoundTag()));
//        }
//    }
//
//    public <C extends Component> Map<BlockPos, C> get(ComponentKey<C> type) {
//        //noinspection unchecked
//        return (Map<BlockPos, C>) providers.computeIfAbsent(type, __ -> new HashMap<>());
//    }
//}
