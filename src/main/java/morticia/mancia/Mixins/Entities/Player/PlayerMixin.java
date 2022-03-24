package morticia.mancia.Mixins.Entities.Player;

import com.mojang.authlib.GameProfile;
import morticia.mancia.Abilities.Ability;
import morticia.mancia.Abilities.AbilityUtil;
import morticia.mancia.Alignment.AlignmentUtil;
import morticia.mancia.Alignment.ManciaAlignment;
import morticia.mancia.PlayerDataAdditions;
import morticia.mancia.Utils.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends Entity implements PlayerDataAdditions {
    ManciaAlignment alignment;
    int level;
    float auraStrength;
    float maxAuraStrength;
    float auraRegenRate;
    Ability activeAbility;
    Ability passiveAbility;
    List<Ability> availableAbilities;

    boolean isInitialized = false;

    PlayerEntity player;

    public PlayerMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    public void initialize() {
        if (this.isInitialized) {
            return;
        }
        System.out.println("Mancia Data Initialized");
        this.alignment = ManciaAlignment.NEUTRAL;
        this.level = 0;
        this.auraStrength = 0.0F;
        this.maxAuraStrength = 0.0f;
        this.auraRegenRate = 0.0F;
        this.activeAbility = AbilityUtil.getNull();
        this.passiveAbility = AbilityUtil.getNull();
        this.availableAbilities = new ArrayList<>();
        this.isInitialized = true;
    }

    // TODO: 3/24/22 Remove the extraneous logic
    public ManciaAlignment m_getAlignment() {
        return this.alignment;
    }

    public void m_setAlignment(ManciaAlignment alignment) {
        this.alignment = alignment;
        System.out.println("Alignment set to: " + this.alignment);
    }

    public Optional<Integer> m_getLevel() {
        return Optional.of(this.level);
    }

    public void m_setLevel(int level) {
        this.level = level;
    }

    public Optional<Float> m_getAuraStrength() {
        return Optional.of(auraStrength);
    }

    public void m_setAuraStrength(float strength) {
        this.auraStrength = strength;
    }

    public Optional<Float> m_getMaxAuraStrength() {
        return Optional.of(this.maxAuraStrength);
    }

    public void m_setMaxAuraStrength(float strength) {
        this.maxAuraStrength = strength;
    }

    public Optional<Float> m_getAuraRegenRateh() {
        return Optional.of(auraRegenRate);
    }

    public void m_setAuraRegenRate(float rate) {
        this.auraRegenRate = rate;
    }

    public Optional<Ability> m_getActiveAbility() {
        return Optional.of(activeAbility);
    }

    public void m_setActiveAbility(Ability ability) {
        this.activeAbility = ability;
    }

    public Optional<Ability> m_getPassiveAbility() {
        return Optional.of(this.passiveAbility);
    }

    public void m_setPassiveAbility(Ability ability) {
        this.passiveAbility = ability;
    }

    public Optional<List<Ability>> m_getAvailableAbilities() {
        return Optional.of(this.availableAbilities);
    }

    public void m_setAvailableAbilities(List<Ability> abilities) {
        this.availableAbilities = abilities;
    }

    // Synchronizes data between client and server
    public void synchronize() {
        if (this.world.isClient) {
            ClientPlayNetworking.send(Constants.REQUEST_PLAYER_DATA, PacketByteBufs.empty());
        } else {
            NbtCompound nbt = writeManciaNbt();

            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeNbt(nbt);

            ServerPlayNetworking.send((ServerPlayerEntity) player, Constants.PLAYER_DATA_SYNC, buf);
        }
    }

    // Sets server to client data, used by client exclusively
    public void setServerData() {
        if (!world.isClient) {
            return;
        }

        NbtCompound nbt = writeManciaNbt();

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(nbt);

        ClientPlayNetworking.send(Constants.SET_SERVER_PLAYER_DATA, buf);
    }

    public void readManciaNbt(NbtCompound nbt) {
        this.isInitialized = nbt.getBoolean("manciaIsInitialized");
        initialize();
        this.alignment = AlignmentUtil.convert(nbt.getInt("manciaAlignment"));
        this.level = nbt.getInt("manciaLevel");
        this.auraStrength = nbt.getFloat("manciaAuraStrength");
        this.maxAuraStrength = nbt.getFloat("manciaMaxAuraStrength");
        this.auraRegenRate = nbt.getFloat("manciaAuraRegenRate");
        this.activeAbility = AbilityUtil.convert(nbt.getInt("manciaActiveAbility"));
        this.passiveAbility = AbilityUtil.convert(nbt.getInt("manciaPassiveAbility"));
        int[] abilitiesInt = nbt.getIntArray("manciaAvailableAbilities");
        this.availableAbilities = new ArrayList<>();
        for (int i : abilitiesInt) {
            this.availableAbilities.add(AbilityUtil.convert(i));
        }
    }

    public NbtCompound writeManciaNbt(NbtCompound nbt) {
        nbt.putInt("manciaAlignment", AlignmentUtil.convert(this.alignment));
        nbt.putInt("manciaLevel", this.level);
        nbt.putFloat("manciaAuraStrength", this.auraStrength);
        nbt.putFloat("manciaMaxAuraStrength", this.maxAuraStrength);
        nbt.putFloat("manciaAuraRegenRate", this.auraRegenRate);
        if (this.activeAbility != null) {
            nbt.putInt("manciaActiveAbility", this.activeAbility.id);
            nbt.putInt("manciaPassiveAbility", this.passiveAbility.id);
            nbt.putIntArray("manciaAvailableAbilities", this.availableAbilities.stream().mapToInt(i->i.id).toArray());
        } else {
            System.out.println("Active ability is null");
            nbt.putInt("manciaActiveAbility", 1);
            nbt.putInt("manciaPassiveAbility", 1);
            nbt.putIntArray("manciaAvailableAbilities", new int[]{1});
        }
        nbt.putBoolean("manciaIsInitialized", this.isInitialized);

        return nbt;
    }

    public NbtCompound writeManciaNbt() {
        return writeManciaNbt(new NbtCompound());
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;FLcom/mojang/authlib/GameProfile;)V", at = @At("TAIL"))
    private void constructorInject(World world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {
        this.player = ((PlayerEntity) (Object) this);
    }

    @Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
    private void readMyDataFromPlayerNbt(NbtCompound nbt, CallbackInfo info) {
        readManciaNbt(nbt);
    }

    @Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
    private void writeMyDataToPlayerNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt = writeManciaNbt(nbt);
    }
}
