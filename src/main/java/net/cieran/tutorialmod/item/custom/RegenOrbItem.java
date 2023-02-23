package net.cieran.tutorialmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RegenOrbItem extends Item {
    public RegenOrbItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (pLevel.isClientSide()) return;
        Player player = ((Player) pEntity);
        if (isItemInOffHand(player)) {
            addRegenToPlayer(player);
        }
    }

    public boolean isItemInOffHand(Player player) {
        ItemStack offHandItem = player.getOffhandItem();
        return offHandItem.getItem().getRegistryName().toString().equals("tutorialmod:regen_orb");
    }

    public void addRegenToPlayer(Player player) {
        boolean playerHasRegen = player.hasEffect(MobEffects.REGENERATION);
        if (playerHasRegen) return;
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
    }
}

