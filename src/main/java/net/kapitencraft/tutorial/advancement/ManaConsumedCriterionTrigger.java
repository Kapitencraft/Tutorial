package net.kapitencraft.tutorial.advancement;

import com.google.gson.JsonObject;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ManaConsumedCriterionTrigger extends SimpleCriterionTrigger<ManaConsumedCriterionTrigger.Instance> {
    private static final ResourceLocation LOCATION = TutorialMod.res("mana_consumed");

    public void trigger(ServerPlayer pPlayer, double amount) {
        this.trigger(pPlayer, instance -> instance.enough(amount));
    }

    public Instance exactly(double value) {
        return new Instance(ContextAwarePredicate.ANY, MinMaxBounds.Doubles.exactly(value));
    }

    public Instance above(double value) {
        return new Instance(ContextAwarePredicate.ANY, MinMaxBounds.Doubles.atLeast(value));
    }

    public Instance below(double value) {
        return new Instance(ContextAwarePredicate.ANY, MinMaxBounds.Doubles.atMost(value));
    }

    public Instance any() {
        return new Instance(ContextAwarePredicate.ANY, MinMaxBounds.Doubles.ANY);
    }

    @Override
    protected Instance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        MinMaxBounds.Doubles bounds = MinMaxBounds.Doubles.fromJson(jsonObject.get("bounds"));
        return new Instance(contextAwarePredicate, bounds);
    }

    @Override
    public ResourceLocation getId() {
        return LOCATION;
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Doubles bounds;

        public Instance(ContextAwarePredicate player, MinMaxBounds.Doubles bounds) {
            super(LOCATION, player);
            this.bounds = bounds;
        }

        public boolean enough(double mana) {
            return bounds.matches(mana);
        }
    }
}
