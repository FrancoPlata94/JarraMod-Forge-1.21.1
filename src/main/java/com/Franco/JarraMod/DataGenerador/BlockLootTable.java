package com.Franco.JarraMod.DataGenerador;

import com.Franco.JarraMod.Bloques.ModBloques;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class BlockLootTable extends BlockLootSubProvider {
    protected BlockLootTable( HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropWhenSilkTouch(ModBloques.JARRA_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBloques.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
