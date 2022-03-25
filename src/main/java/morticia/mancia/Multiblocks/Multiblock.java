package morticia.mancia.Multiblocks;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

// This class is a utility class to abstract some of this stuff and make it easy to check
// for certain things during rituals. Instances don't refer to actual instances of these
// in the world but rather a template with which to check positions in the world.
public class Multiblock {
    String designation;
    int id; // This is so I can serialize these efficiently, like with Ability
    // Dimensions
    int height;
    int width;
    int depth;

    Block[][][] blocks;

    public Multiblock(String designation, int height, int width, int depth, Block[][][] blocks) {
        this.designation = designation;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.blocks = blocks;
        this.id = MultiblockUtil.assignId();
    }

    // String array can be used to give the ids of blocks and have it converted
    public Multiblock(String designation, int height, int width, int depth, String[][][] blocksStr) {
        this.designation = designation;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.id = MultiblockUtil.assignId();
        blocks = new Block[blocksStr.length][][];
        for (int i = 0; i < blocksStr.length; i++) {
            blocks[i] = new Block[blocksStr[i].length][];
            for (int j = 0; j < blocksStr[i].length; j++) {
                blocks[i][j] = new Block[blocksStr[i][j].length];
                for (int k = 0; k < blocksStr[i][j].length; k++) {
                    this.blocks[i][j][k] = Registry.BLOCK.get(new Identifier(blocksStr[i][j][k]));
                }
            }
        }
    }

    public Multiblock(String designation, int height, int width, int depth, String[][][] blocksStr, HashMap<String, String> key) {
        this.designation = designation;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.id = MultiblockUtil.assignId();
        blocks = new Block[blocksStr.length][][];
        for (int i = 0; i < blocksStr.length; i++) {
            blocks[i] = new Block[blocksStr[i].length][];
            for (int j = 0; j < blocksStr[i].length; j++) {
                blocks[i][j] = new Block[blocksStr[i][j].length];
                for (int k = 0; k < blocksStr[i][j].length; k++) {
                    this.blocks[i][j][k] = Registry.BLOCK.get(new Identifier(key.get(blocksStr[i][j][k])));
                }
            }
        }
    }

    /**
     * Checks to see if this multiblock structure exists at specified location
     *
     * @param world The world it's checking in
     * @param pos The position the structure is at, this parameter should refer to the top left corner
     * @return Whether the structure is valid
     */
    public boolean check(World world, BlockPos pos) {
        BlockPos currPos = pos;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < depth; k++) {
                    currPos = pos.add(i, j, k);
                    if (!world.getBlockState(currPos).isOf(blocks[i][j][k])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
