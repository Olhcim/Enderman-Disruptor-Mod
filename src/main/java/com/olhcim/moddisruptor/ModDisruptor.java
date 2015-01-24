package com.olhcim.moddisruptor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModDisruptor.MODID, version = ModDisruptor.VERSION)
public class ModDisruptor
{
	public static final String MODID = "moddisruptor";
	public static final String VERSION = "0.1";

	public static Block blockDisruptor;

	@Instance(MODID)
	public static ModDisruptor instance;


	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		blockDisruptor = new BlockDisruptor(Material.ground)
				.setHardness(0.5f)
				.setStepSound(Block.soundTypeMetal)
				.setBlockName("disruptor")
				.setCreativeTab(CreativeTabs.tabMisc)
				.setBlockTextureName(MODID + ":" + "disruptor")
				.setLightLevel(0.5f)
				.setResistance(2000.0F);
		
		GameRegistry.registerBlock(blockDisruptor, "disruptor");
		GameRegistry.registerTileEntity(TileEntityDisruptor.class, "tileEntityDisruptor");
		
		GameRegistry.addShapedRecipe(new ItemStack(blockDisruptor), "IOI", "OEO", "IOI", 'I',Blocks.iron_bars, 'O', Blocks.obsidian, 'E', Items.ender_eye);
		
		MinecraftForge.EVENT_BUS.register(new EventHandlerTeleport());
	}
}
