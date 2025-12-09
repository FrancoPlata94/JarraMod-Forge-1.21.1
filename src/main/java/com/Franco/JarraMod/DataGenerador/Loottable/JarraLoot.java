package com.Franco.JarraMod.DataGenerador.Loottable;

import com.Franco.JarraMod.JarraMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class JarraLoot implements LootTableSubProvider {

    public JarraLoot(HolderLookup.Provider provider) {
        super();
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> pOutput) {
        ResourceLocation l = ResourceLocation.fromNamespaceAndPath(JarraMod.MOD_ID, "block/jarra_loottable");
        ResourceKey<LootTable> k = ResourceKey.create(Registries.LOOT_TABLE, l);
        pOutput.accept(k,LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(2.0F, 4.0F))
                                .add(LootItem.lootTableItem(Items.DIAMOND)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD)
                                        .setWeight(7)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,4))))
                                .add(LootItem.lootTableItem(Items.DISC_FRAGMENT_5)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2))))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4,5))))
                                .add(LootItem.lootTableItem(Items.SCULK))
                        )
        );
    }
}
