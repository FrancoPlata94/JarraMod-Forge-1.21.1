package com.Franco.JarraMod.Bloques;

import com.Franco.JarraMod.ItemMod.ModItems;
import com.Franco.JarraMod.JarraMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBloques {
    public static final DeferredRegister<Block> BLOCKS= DeferredRegister.create(ForgeRegistries.BLOCKS, JarraMod.MOD_ID);

    public static final RegistryObject<Block> JARRA_BLOCK=registrarBloque("jarra_block",()->new JarraBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.BAMBOO)));

    private static <T extends Block> RegistryObject<T> registrarBloque(String name, Supplier<T> block){
        RegistryObject<T> R= BLOCKS.register(name,block);
        registarBlockItem(name,R);
        return R;
    }

    private static <T extends Block> void registarBlockItem(String name,RegistryObject<T> block){
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),new Item.Properties()));
    }
    public static void register(IEventBus event){
        BLOCKS.register(event);
    }



}
