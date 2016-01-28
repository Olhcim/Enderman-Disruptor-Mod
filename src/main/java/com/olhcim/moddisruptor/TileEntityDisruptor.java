package com.olhcim.moddisruptor;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
		return true;
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

	@Override
	public void updateEntity() {
		World world = this.getWorldObj();
		if(world.isRemote) {
			int x = this.xCoord;
			int y = this.yCoord;
			int z = this.zCoord;

			if(world.isBlockIndirectlyGettingPowered(x,y,z)) {

				for (int i = 0; i < BlockDisruptor.range * 4; i++) {


					float ox = random(-1, 1);
					float oy = random(-1, 1);
					float oz = random(-1, 1);

					float dist = 1f / (float) Math.sqrt(ox * ox + oy * oy + oz * oz);

					ox *= dist * BlockDisruptor.range;
					oy *= dist * BlockDisruptor.range;
					oz *= dist * BlockDisruptor.range;

					ox += 0.5f;
					oy += 0.5f;
					oz += 0.5f;

					world.spawnParticle("enchantmenttable", x + ox, y + oy, z + oz, 0, 0, 0);
				}
			}
		}
	}

	private float random(float a, float b) {
		return (float)Math.random() * (b - a) + a;
	}
}