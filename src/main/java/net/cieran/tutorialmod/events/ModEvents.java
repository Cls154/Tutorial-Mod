package net.cieran.tutorialmod.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.cieran.tutorialmod.TutorialMod;
import net.cieran.tutorialmod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void setEntityOnFireWhenHitWithNetherBrick(LivingDamageEvent event) {
        if (event.getEntity().level.isClientSide()) return;
        if (!(event.getSource().getDirectEntity() instanceof Player)) return;
        Player player = ((Player) event.getSource().getDirectEntity());
        if (player.getMainHandItem().getItem() == Items.NETHER_BRICK) {
            player.getMainHandItem().shrink(1);
            event.getEntityLiving().setSecondsOnFire(2);
        }
    }

    @SubscribeEvent
    public static void fullNetherriteArmorGivesResistance(PlayerTickEvent event) {
        if (event.player.level.isClientSide()) return;
        if (!(event.player instanceof Player)) return;
        Player player = event.player;
        boolean hasArmorEffect = player.hasEffect(MobEffects.DAMAGE_RESISTANCE);
        if (hasFullArmorSetOn(player, ArmorMaterials.NETHERITE) && !hasArmorEffect) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 2));
        }
    }

    private static boolean hasFullArmorSetOn(Player player, ArmorMaterials material) {
        for (ItemStack armorItem : player.getInventory().armor) {
            if (!(armorItem.getItem() instanceof ArmorItem)) return false;
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == material &&
                leggings.getMaterial() == material &&
                chestplate.getMaterial() == material &&
                helmet.getMaterial() == material;
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.EGG_PLANT.get(), 12);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), stack, 10, 8, 0.02F));
        }

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.CITRINE_PICKAXE.get(), 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 12), stack, 4, 12, 0.09F));
        }
    }

}
