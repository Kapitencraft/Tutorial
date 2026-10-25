package net.kapitencraft.tutorial.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public interface ModCriterionTriggers {
    ManaConsumedCriterionTrigger MANA_CONSUMED = CriteriaTriggers.register(new ManaConsumedCriterionTrigger());

    static void init() {
    }
}
