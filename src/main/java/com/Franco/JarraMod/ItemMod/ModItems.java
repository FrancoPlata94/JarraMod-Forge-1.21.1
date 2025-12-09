package com.Franco.JarraMod.ItemMod;

import com.Franco.JarraMod.JarraMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, JarraMod.MOD_ID);
    public static void register(IEventBus modBus){
        ITEMS.register(modBus);
    }

}
