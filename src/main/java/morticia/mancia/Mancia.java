package morticia.mancia;

import morticia.mancia.Abilities.AbilityUtil;
import morticia.mancia.Multiblocks.MultiblockUtil;
import morticia.mancia.Registry.ModItems;
import morticia.mancia.Utils.Constants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class Mancia implements ModInitializer {
    public static String MOD_ID = "mancia";

    @Override
    public void onInitialize() {
        ModItems.initItems();
        AbilityUtil.initAbilities();
        MultiblockUtil.initMultiblocks();

        // Register server packet responses
        ServerPlayNetworking.registerGlobalReceiver(Constants.REQUEST_PLAYER_DATA,
                ((server, player, handler, buf, responseSender) -> server.execute(() -> {
                    System.out.println("Player Data Request Received");
                    PacketByteBuf sendBuf = PacketByteBufs.create();
                    sendBuf.writeNbt(player.writeManciaNbt());
                    responseSender.sendPacket(Constants.PLAYER_DATA_SYNC, sendBuf);
                })));
        // Set custom player data, client to server
        ServerPlayNetworking.registerGlobalReceiver(Constants.SET_SERVER_PLAYER_DATA,
                ((server, player, handler, buf, responseSender) -> {
                    NbtCompound nbt  = buf.readNbt();
                    server.execute(() -> {
                        System.out.println("Nbt Set Server Side");
                        player.readManciaNbt(nbt);
                    });
                }));
    }
}
