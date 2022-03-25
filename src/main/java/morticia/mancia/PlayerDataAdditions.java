package morticia.mancia;

import morticia.mancia.Abilities.Ability;
import morticia.mancia.Alignment.ManciaAlignment;
import net.minecraft.nbt.NbtCompound;

import java.util.List;

public interface PlayerDataAdditions {
    default void initialize() {

    }

    default ManciaAlignment m_getAlignment() {
        return null;
    }

    default void m_setAlignment(ManciaAlignment alignment) {

    }

    default Integer m_getLevel() {
        return null;
    }

    default void m_setLevel(int level) {

    }

    default Float m_getAuraStrength() {
        return null;
    }

    default void m_setAuraStrength(float strength) {

    }

    default Float m_getMaxAuraStrength() {
        return null;
    }

    default void m_setMaxAuraStrength(float strength) {

    }

    default Float m_getAuraRegenRate() {
        return null;
    }

    default void m_setAuraRegenRate(float rate) {

    }

    default Ability m_getActiveAbility() {
        return null;
    }

    default void m_setActiveAbility(Ability ability) {

    }

    default Ability m_getPassiveAbility() {
        return null;
    }

    default void m_setPassiveAbility(Ability ability) {

    }

    default List<Ability> m_getAvailableAbilities() {
        return null;
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
