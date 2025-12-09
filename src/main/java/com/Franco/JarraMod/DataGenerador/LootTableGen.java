package com.Franco.JarraMod.DataGenerador;

import com.Franco.JarraMod.DataGenerador.Loottable.JarraLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootTableGen extends LootTableProvider{

    public LootTableGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLootTable::new, LootContextParamSets.BLOCK),
                new SubProviderEntry( JarraLoot::new, LootContextParamSets.CHEST)
        ), lookup);
    }



}
