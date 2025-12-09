package com.Franco.JarraMod.Bloques;

import com.Franco.JarraMod.JarraMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY=DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,JarraMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<JarraBlockEntity>> JARRA_BLOCK_ENTITY=
            BLOCK_ENTITY.register("jarra_block_entity",() -> BlockEntityType.Builder.of(JarraBlockEntity::new,ModBloques.JARRA_BLOCK.get()).build(null));
    public static void register(IEventBus event){
        BLOCK_ENTITY.register(event);
    }
}
