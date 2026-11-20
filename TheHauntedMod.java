package com.example.thehaunted;

import com.example.thehaunted.block.ModBlocks;
import com.example.thehaunted.event.HauntedEvents;
import com.example.thehaunted.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TheHauntedMod.MOD_ID)
public class TheHauntedMod {
    public static final String MOD_ID = "thehaunted";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TheHauntedMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HauntedEvents());
        LOGGER.info("THE HAUNTED mod loaded. Beware the shadows...");
    }
}
