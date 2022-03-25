package morticia.mancia.Multiblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiblockUtil {
    public static List<Multiblock> multiblocks = new ArrayList<>();
    public static int idCount = 0;

    // Called to initialize the list
    public static void initMultiblocks() {
        HashMap<String, String> key = new HashMap<>();
        // solid 3x3x3 cube of redstone blocks
        key.put("x", "minecraft:redstone_block");
        String[][][] array = new String[][][]{
                {{"x", "x", "x"}, {"x", "x", "x"}, {"x", "x", "x"}},
                {{"x", "x", "x"}, {"x", "x", "x"}, {"x", "x", "x"}},
                {{"x", "x", "x"}, {"x", "x", "x"}, {"x", "x", "x"}}};
        multiblocks.add(new Multiblock("test_multi", 3, 3, 3, array, key));
    }

    public static int assignId() {
        idCount++;
        return idCount;
    }

    public static Multiblock get(int id) {
        for (Multiblock i : multiblocks) {
            if (i.id == id) {
                return i;
            }
        }
        return null;
    }

    public static Multiblock get(String designation) {
        for (Multiblock i : multiblocks) {
            if (i.designation.equals(designation)) {
                return i;
            }
        }
        return null;
    }
}
