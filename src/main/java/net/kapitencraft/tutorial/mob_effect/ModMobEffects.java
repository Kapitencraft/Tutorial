package net.kapitencraft.tutorial.mob_effect;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public interface ModMobEffects {
    DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, TutorialMod.MOD_ID);

    RegistryObject<MobEffect> FLYING = REGISTRY.register("flying", FlyingMobEffect::new);
}
