package com.olhcim.moddisruptor;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.monster.EntityEnderman;


public class RegistryDisruptors {
	
	private static ArrayList<TileEntityDisruptor> disruptors = new ArrayList<TileEntityDisruptor>();
	
	
	public static void register(TileEntityDisruptor te)
	{
		disruptors.add(te);
	}
	
	public static void unregister(TileEntityDisruptor te)
	{
		disruptors.remove(te);
	}

	public static boolean contains(TileEntityDisruptor te)
	{
		return disruptors.contains(te);
	}
	
	public static boolean isEntityNear(EntityEnderman e)
	{
		for (TileEntityDisruptor te : disruptors)
		{
			if(te.isNear(e))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void validate()
	{
		Iterator<TileEntityDisruptor> iter = disruptors.iterator(); //avoids cuncrent modification exception

		while (iter.hasNext()) {
			TileEntityDisruptor te = iter.next();

			if (te.isInvalid() || te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord) != ModDisruptor.blockDisruptor)
			{
				iter.remove();
			}
		}
	}
}
