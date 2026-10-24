package net.kapitencraft.tutorial.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.UUID;

public class CustomItem extends Item {
    private static final UUID MODIFIER_ID = UUID.randomUUID();

    public CustomItem() {
        super(new Properties().rarity(Rarity.RARE).durability(100000));
    }

    public static int getColor(ItemStack stack, int index) {
        if (index != 0) return -1;
        LocalPlayer player = Minecraft.getInstance().player;
        int y = (int) player.getY() + 64;
        int color = y * 255 / 384;
        return (color << 8 | color) << 8 | color;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            modifiers.put(ModAttributes.HEAL_ATTRIBUTE.get(), new AttributeModifier(MODIFIER_ID, "custom modifier", 100, AttributeModifier.Operation.ADDITION));
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }
}
