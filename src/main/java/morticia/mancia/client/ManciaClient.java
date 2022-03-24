package morticia.mancia.client;

import morticia.mancia.Keybindings.Keybindings;
import morticia.mancia.Utils.Constants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.NbtCompound;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class ManciaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybindings.initKeyBindings();

        // To sync my custom player data
        ClientPlayNetworking.registerGlobalReceiver(Constants.PLAYER_DATA_SYNC,
                (client, handler, buf, responseSender) -> {
                    System.out.println("Player Data Sync Packet Received");
                    NbtCompound maciaNbt = buf.readNbt();
                    client.execute(() -> {
                        if (client.player != null) {
                            client.player.readManciaNbt(maciaNbt);
                        }
                    });
                });
    }
}
