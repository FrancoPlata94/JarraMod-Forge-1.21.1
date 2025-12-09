package com.Franco.JarraMod;

import com.Franco.JarraMod.Bloques.ModBlockEntity;
import com.Franco.JarraMod.Bloques.ModBloques;
import com.Franco.JarraMod.ItemMod.ModItems;
import com.Franco.JarraMod.particulas.JarraParticula;
import com.Franco.JarraMod.particulas.ModParticula;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JarraMod.MOD_ID)
public class JarraMod
{
    public static final String MOD_ID = "jarramod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public JarraMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModItems.register(modEventBus);
        ModBloques.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModParticula.register(modEventBus);
        //comando para generar la Vasija con Loottable, dentro de minecraft
        // /setblock ~ ~ ~ jarramod:jarra_block{LootTable:"jarramod:block/jarra_loottable"}
    }

    public JarraMod(HolderLookup.Provider provider) {
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey()== CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBloques.JARRA_BLOCK);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void registrarParticulasProvedor(RegisterParticleProvidersEvent event){
            event.registerSpriteSet(ModParticula.JARRA_PARTICULA.get(), JarraParticula.Provider::new);
        }
    }
}
