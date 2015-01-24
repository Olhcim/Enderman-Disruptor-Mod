package com.olhcim.moddisruptor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDisruptor extends Block implements ITileEntityProvider
{
	public static final int range = 16;

	public BlockDisruptor(Material p_i45394_1_) {
		super(p_i45394_1_);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon( ModDisruptor.MODID.toLowerCase() + ":" + "disruptor");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityDisruptor();
	}
}
