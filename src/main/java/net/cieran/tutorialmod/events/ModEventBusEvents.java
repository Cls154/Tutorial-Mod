package net.cieran.tutorialmod.events;

import net.cieran.tutorialmod.TutorialMod;
import net.cieran.tutorialmod.entity.ModEntityTypes;
import net.cieran.tutorialmod.entity.custom.RaccoonEntity;
import net.cieran.tutorialmod.events.loots.CoalCokeFromCreeperAdditionModifier;
import net.cieran.tutorialmod.events.loots.DowsingRodIglooAdditionModifier;
import net.cieran.tutorialmod.events.loots.EggPlantSeedsFromGrassAdditionModifier;
import net.cieran.tutorialmod.particle.ModParticles;
import net.cieran.tutorialmod.particle.custom.CitrineParticles;
import net.cieran.tutorialmod.recipe.GemCuttingStationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModiferSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new EggPlantSeedsFromGrassAdditionModifier.Serializer().setRegistryName(
                        new ResourceLocation(TutorialMod.MOD_ID, "egg_plant_seeds_from_grass")
                ),
                new DowsingRodIglooAdditionModifier.Serializer().setRegistryName(
                        new ResourceLocation(TutorialMod.MOD_ID, "dowsing_rod_in_igloo")
                ),
                new CoalCokeFromCreeperAdditionModifier.Serializer().setRegistryName(
                        new ResourceLocation(TutorialMod.MOD_ID, "coal_coke_from_creeper")
                )
        );
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, GemCuttingStationRecipe.Type.ID, GemCuttingStationRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.CITRINE_PARTICLES.get(), CitrineParticles.Provider::new);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.setAttributes());
    }
}
