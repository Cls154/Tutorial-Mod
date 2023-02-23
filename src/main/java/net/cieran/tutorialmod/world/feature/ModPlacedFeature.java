package net.cieran.tutorialmod.world.feature;

import net.cieran.tutorialmod.world.feature.ore.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;


public class ModPlacedFeature {
    public static final Holder<PlacedFeature> EBONY_PLACED =
            PlacementUtils.register("ebony_placed", ModConfiguredFeatures.EBONY_SPAWN,
                    VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2)));

    public static final Holder<PlacedFeature> PINK_ROSE_PLACED =
            PlacementUtils.register("pink_rose_placed", ModConfiguredFeatures.FLOWER_PINK_ROSE,
                    RarityFilter.onAverageOnceEvery(1),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome());

    public static final Holder<PlacedFeature> ORE_CITRINE_PLACED =
            PlacementUtils.register("ore_citrine_placed", ModConfiguredFeatures.ORE_CITRINE,
                    ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));


}
