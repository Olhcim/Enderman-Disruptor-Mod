package com.olhcim.moddisruptor;

import com.olhcim.moddisruptor.disruption.BlockDisruptor;
import com.olhcim.moddisruptor.disruption.EventHandlerTeleport;
import com.olhcim.moddisruptor.util.VersionCheck;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModDisruptor.MODID, version = ModDisruptor.VERSION)
public class ModDisruptor {
    public static final String MODID = "moddisruptor";
    public static final String VERSION = "0.3.0";

    public static BlockDisruptor blockDisruptor;
    public static CreativeTabs creativeTab;

    @Mod.Instance(MODID)
    public static ModDisruptor instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.load(event);

        if(Config.checkVersion)
            VersionCheck.registerAndCheck();

        creativeTab = new MyTab(blockDisruptor);
        blockDisruptor = new BlockDisruptor();
        MinecraftForge.EVENT_BUS.register(new EventHandlerTeleport());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        blockDisruptor.init(event);
    }
}
