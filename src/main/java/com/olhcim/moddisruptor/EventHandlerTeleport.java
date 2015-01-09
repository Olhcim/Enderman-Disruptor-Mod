package com.olhcim.moddisruptor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EventHandlerTeleport {
	
	@SubscribeEvent
	public void onEvent(EnderTeleportEvent e)
	{
		if (e.entity instanceof EntityEnderman)
		{
			EntityEnderman eman = (EntityEnderman) e.entity;
			
			RegistryDisruptors.validate();
			
			if ( RegistryDisruptors.isEntityNear(eman) )
			{
				e.setCanceled(true);
			}
		}
	}
	
}
