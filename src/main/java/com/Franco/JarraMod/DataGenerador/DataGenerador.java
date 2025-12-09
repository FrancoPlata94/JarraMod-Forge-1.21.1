package com.Franco.JarraMod.DataGenerador;

import com.Franco.JarraMod.JarraMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = JarraMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerador {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generador=event.getGenerator();
        PackOutput po=generador.getPackOutput();
        ExistingFileHelper eh= event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lp=event.getLookupProvider();
        generador.addProvider(event.includeServer(),new LootTableGen(po,lp));

    }
}
