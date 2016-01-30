package com.olhcim.moddisruptor.disruption;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EventHandlerTeleport {

    private Random rand;

    public EventHandlerTeleport() {
        rand = new Random();
    }

    @SubscribeEvent
    public void onEvent(EnderTeleportEvent event) {
        if (event.entity instanceof EntityEnderman) {
            EntityEnderman enderman = (EntityEnderman) event.entity;

            if (RegistryDisruptors.isEntityNear(enderman)) {
                event.setCanceled(true);
            }

            float chance = 1f/20f;
            if(rand.nextFloat() < chance) {
                stationaryTeleport(enderman);
            }
        }
    }

    /**
     * Modified teleport code taken from EntityEnderman, used for spawning sound and particles
     * @param enderman
     */
    private void stationaryTeleport(EntityEnderman enderman) {
        for (int i = 0; i < 128; ++i) {
            float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double x = enderman.posX + (this.rand.nextDouble() - 0.5D) * (double)enderman.width * 2.0D;
            double y = enderman.posY + this.rand.nextDouble() * (double)enderman.height;
            double z = enderman.posZ + (this.rand.nextDouble() - 0.5D) * (double)enderman.width * 2.0D;
            enderman.worldObj.spawnParticle(EnumParticleTypes.PORTAL, x, y, z, (double)f, (double)f1, (double)f2);
        }

        enderman.worldObj.playSoundEffect(enderman.posX, enderman.posY, enderman.posZ, "mob.endermen.portal", 1.0F, 1.0F);
        enderman.playSound("mob.endermen.portal", 1.0F, 1.0F);
    }

}
