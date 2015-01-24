package com.olhcim.moddisruptor;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDisruptor extends TileEntity {
	
	public TileEntityDisruptor()
	{
		register();
	}
	
	public void register()
	{
		RegistryDisruptors.register( this );
	}
	
	public void unregister()
	{
		RegistryDisruptors.unregister( this );
	}
	
	@Override
	public boolean canUpdate()
	{
		return false;
	}
	
	@Override
	public void onChunkUnload()
    {
		unregister();
    }
	
	public boolean isNear(EntityEnderman e)
	{
		return e.getDistance(this.xCoord, this.yCoord, this.zCoord) <= BlockDisruptor.range && e.worldObj == this.worldObj;
	}
}