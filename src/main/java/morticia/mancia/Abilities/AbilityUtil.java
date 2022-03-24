package morticia.mancia.Abilities;

import java.util.ArrayList;
import java.util.List;

public class AbilityUtil {
    public static List<Ability> abilityList = new ArrayList<>();

    public static void initAbilities() {

    }

    public static Ability convert(String id) {
        for (Ability i : abilityList) {
            if (i.abilityName.equals(id)) {
                return i;
            }
        }
        return null;
    }


    public static String convert(Ability id) {
        return id.abilityName;
    }
}
