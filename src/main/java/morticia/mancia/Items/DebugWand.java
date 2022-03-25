package morticia.mancia.Items;

import morticia.mancia.Multiblocks.Multiblock;
import morticia.mancia.Multiblocks.MultiblockUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;

public class DebugWand extends Item {
    public DebugWand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) {
            return super.useOnBlock(context);
        }
        Multiblock m = MultiblockUtil.get(1);
        context.getPlayer().sendMessage(new LiteralText("Checking..."), false);
        context.getPlayer().sendMessage(new LiteralText(m.check(context.getWorld(), context.getBlockPos()) ? "true" : "false"), false);
        return super.useOnBlock(context);
    }
}
