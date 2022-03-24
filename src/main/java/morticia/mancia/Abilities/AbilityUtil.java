package morticia.mancia.Abilities;

import java.util.ArrayList;
import java.util.List;

public class AbilityUtil {
    public static List<Ability> abilityList = new ArrayList<>();
    private static int idCount = 0;
    private static Ability nullAbility;

    public static void initAbilities() {
        nullAbility = new Ability("null");
        abilityList.add(nullAbility);
    }

    public static Ability getNull() {
        return nullAbility;
    }

    public static int assignId() {
        idCount++;
        return idCount;
    }

    public static Ability convert(String id) {
        for (Ability i : abilityList) {
            if (i.abilityName.equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static Ability convert(int id) {
        for (Ability i : abilityList) {
            if (i.id == id) {
                return i;
            }
        }
        return null;
    }


    public static String convert(Ability id) {
        return id.abilityName;
    }
}
