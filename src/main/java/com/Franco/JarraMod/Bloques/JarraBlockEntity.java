package com.Franco.JarraMod.Bloques;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JarraBlockEntity extends BlockEntity {
    @Nullable
    protected ResourceKey<LootTable> lootTable;
    protected long lootTableSeed = 0L;
    public JarraBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntity.JARRA_BLOCK_ENTITY.get(), pPos, pBlockState);

    }
    public final ItemStackHandler inventario=new ItemStackHandler(20) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public void obtenerLootTable(@Nullable Player player) {
        if (this.lootTable != null && this.level != null && !this.level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) this.level;
            LootTable table = serverLevel.getServer().reloadableRegistries().getLootTable(this.lootTable);
            LootParams.Builder params = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.worldPosition));

            if (player != null) {
                params.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
            }
            List<ItemStack> items = table.getRandomItems(params.create(LootContextParamSets.CHEST), this.lootTableSeed);
            for (net.minecraft.world.item.ItemStack item : items) {
                for (int i = 0; i < inventario.getSlots(); i++) {
                    if (inventario.insertItem(i, item, false).isEmpty()) {
                        break;
                    }
                }
            }
            this.lootTable = null;
            this.lootTableSeed = 0L;
            setChanged();
        }
    }


    public void dropeo(){
        obtenerLootTable(null);
        SimpleContainer n=new SimpleContainer(inventario.getSlots());
        for (int i = 0; i < inventario.getSlots(); i++) {
            n.setItem(i,inventario.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,n);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create((this));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider provider) {
        super.saveAdditional(pTag, provider);
        if (this.lootTable!=null){
            pTag.putString("LootTable",this.lootTable.location().toString());
            if (this.lootTableSeed!=0L){
                pTag.putLong("LootTableSeed",this.lootTableSeed);
            }
        }
        pTag.put("inventario",inventario.serializeNBT(provider));
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider provider) {
        super.loadAdditional(pTag, provider);
        if (pTag.contains("LootTable",8)){
            this.lootTable=ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse(pTag.getString("LootTable")));
            this.lootTableSeed=pTag.getLong("LootTableSeed");
        }
        if (pTag.contains("inventario")) {
            inventario.deserializeNBT(provider, pTag.getCompound("inventario"));
        }
    }
    public boolean hayLugar(){
        for (int i = 0; i < inventario.getSlots()+1; i++) {
            if (inventario.getStackInSlot(i).isEmpty()){
                return true;
            }
        }
        return false;
    }
    public int primerVacio(){
        int i;
        for (i = 0; i < inventario.getSlots(); i++) {
            if (inventario.getStackInSlot(i).isEmpty()){
                return i;
            }
        }
        return i;
    }
    public int ultimoItem(){
        for (int i = inventario.getSlots() - 1; i >= 0; i--) {

            // Verifica si el ItemStack en el slot 'i' NO está vacío.
            if (!inventario.getStackInSlot(i).isEmpty()){
                // ¡Encontrado! Devuelve el índice.
                return i;
            }
        }

        // Si el bucle termina, significa que todos los slots están vacíos.
        return 0;
    }


}
