package net.cieran.tutorialmod.world;

import net.cieran.tutorialmod.TutorialMod;
import net.cieran.tutorialmod.world.gen.ModEntityGeneration;
import net.cieran.tutorialmod.world.gen.ModFlowerGeneration;
import net.cieran.tutorialmod.world.gen.ModOreGeneration;
import net.cieran.tutorialmod.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
        ModTreeGeneration.generateTrees(event);
        ModFlowerGeneration.generateFlowers(event);
        ModEntityGeneration.onEntitySpawn(event);
    }
}
