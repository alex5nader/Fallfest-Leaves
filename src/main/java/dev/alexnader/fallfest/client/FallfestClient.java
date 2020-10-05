package dev.alexnader.fallfest.client;

import dev.alexnader.fallfest.Fallfest;
import dev.alexnader.fallfest.FallfestConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class FallfestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(FallfestConfig.class, JanksonConfigSerializer::new);

        Fallfest.LOG.error("Color Leaves? " + FallfestConfig.getOrLoad().colorLeaves());

        BlockRenderLayerMap.INSTANCE.putBlock(Fallfest.TEST_LEAVES, RenderLayer.getCutoutMipped());

        ColorProviderRegistry.BLOCK.register(new FallColorsProvider(), Fallfest.TEST_LEAVES);
    }
}
