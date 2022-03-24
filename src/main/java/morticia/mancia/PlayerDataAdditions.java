package morticia.mancia;

import morticia.mancia.Abilities.Ability;
import morticia.mancia.Alignment.ManciaAlignment;
import net.minecraft.nbt.NbtCompound;

import java.util.List;
import java.util.Optional;

public interface PlayerDataAdditions {
    default void initialize() {

    }

    default ManciaAlignment m_getAlignment() {
        return null;
    }

    default void m_setAlignment(ManciaAlignment alignment) {

    }

    default Optional<Integer> m_getLevel() {
        return Optional.empty();
    }

    default void m_setLevel(int level) {

    }

    default Optional<Float> m_getAuraStrength() {
        return Optional.empty();
    }

    default void m_setAuraStrength(float strength) {

    }

    default Optional<Float> m_getMaxAuraStrength() {
        return Optional.empty();
    }

    default void m_setMaxAuraStrength(float strength) {

    }

    default Optional<Float> m_getAuraRegenRateh() {
        return Optional.empty();
    }

    default void m_setAuraRegenRate(float rate) {

    }

    default Optional<Ability> m_getActiveAbility() {
        return Optional.empty();
    }

    default void m_setActiveAbility(Ability ability) {

    }

    default Optional<Ability> m_getPassiveAbility() {
        return Optional.empty();
    }

    default void m_setPassiveAbility(Ability ability) {

    }

    default Optional<List<Ability>> m_getAvailableAbilities() {
        return Optional.empty();
    }

    default void m_setAvailableAbilities(List<Ability> abilities) {

    }

    default void synchronize() {

    }

    default void setServerData() {

    }

    default void readManciaNbt(NbtCompound nbt) {

    }

    default NbtCompound writeManciaNbt(NbtCompound nbt) {
        return new NbtCompound();
    }

    default NbtCompound writeManciaNbt() {
        return new NbtCompound();
    }
}
