package morticia.mancia.Abilities;

public class Ability {
    public String abilityName;
    public int id;

    public Ability(String name) {
        this.abilityName = name;
        this.id = AbilityUtil.assignId();
    }

    @Override
    public String toString() {
        return this.abilityName + ": id - " + this.id;
    }
}
