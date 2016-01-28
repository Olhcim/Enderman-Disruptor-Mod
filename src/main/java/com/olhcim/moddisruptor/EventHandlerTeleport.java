package com.olhcim.moddisruptor;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerTeleport {

    @SubscribeEvent
    public void onEvent(EnderTeleportEvent e) {
        if (e.entity instanceof EntityEnderman) {
            EntityEnderman eman = (EntityEnderman) e.entity;

            RegistryDisruptors.validate();

            if (RegistryDisruptors.isEntityNear(eman)) {
                e.setCanceled(true);
            }
        }
    }

}
