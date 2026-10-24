package net.kapitencraft.tutorial.advancement;

import com.google.gson.JsonObject;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;


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

    @Override
    public ResourceLocation getId() {
        return LOCATION;
    }

    public Instance any() {
        return new Instance(ContextAwarePredicate.ANY, MinMaxBounds.Doubles.ANY);
    }

    @Override
    protected @NotNull Instance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext deserializationContext) {
        MinMaxBounds.Doubles d = MinMaxBounds.Doubles.fromJson(json.get("bounds"));
        return new Instance(predicate, d);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Doubles value;

        public Instance(ContextAwarePredicate player, MinMaxBounds.Doubles value) {
            super(LOCATION, player);
            this.value = value;
        }

        public boolean enough(double mana) {
            return value.matches(mana);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext conditions) {
            JsonObject object = super.serializeToJson(conditions);
            object.add("bounds", this.value.serializeToJson());
            return object;
        }
    }
}
