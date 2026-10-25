package net.kapitencraft.tutorial.item.armor;


import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.client.ArmorClientExtension;
import net.kapitencraft.tutorial.item.armor.client.model.WizardHatModel;
import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.kapitencraft.tutorial.item.armor.client.provider.SimpleModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class WizardHatArmorItem extends AbstractArmorItem  {
    private static final String TEXTURE_LOCATION = makeCustomTextureLocation(TutorialMod.MOD_ID, "wizard_hat");

    public WizardHatArmorItem() {
        super(ArmorMaterials.LEATHER, Type.HELMET, new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ArmorClientExtension(new SimpleModelProvider(WizardHatModel::createBodyLayer, WizardHatModel::new)));
    }
}
