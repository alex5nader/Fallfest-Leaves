//package dev.alexnader.fallfest.component;
//
//import dev.onyxstudios.cca.api.v3.component.ComponentContainer;
//import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
//import nerdhub.cardinal.components.api.component.Component;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.world.BlockView;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SuppressWarnings("UnstableApiUsage")
//public class InWorldBlockComponentProvider<C extends Component> implements ComponentProvider {
//    private final World world;
//    private final Map<BlockPos, C> components;
//
//    public InWorldBlockComponentProvider(World world) {
//        this.world = world;
//        this.components = new HashMap<>();
//    }
//
//    @Override
//    public ComponentContainer getComponentContainer() {
//        return null;
//    }
//}
