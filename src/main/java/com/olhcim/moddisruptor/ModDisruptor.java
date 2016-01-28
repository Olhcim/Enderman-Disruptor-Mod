package com.olhcim.moddisruptor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModDisruptor.MODID, version = ModDisruptor.VERSION)
public class ModDisruptor {
    public static final String MODID = "moddisruptor";
    public static final String VERSION = "%version%";
    public static CreativeTabs creativeTab;
    public static BlockDisruptor blockDisruptor;

    @Mod.Instance(MODID)
    public static ModDisruptor instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        creativeTab = new CreativeTabDisruptor();
        blockDisruptor = new BlockDisruptor();
        MinecraftForge.EVENT_BUS.register(new EventHandlerTeleport());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        blockDisruptor.init(event);
    }
}
