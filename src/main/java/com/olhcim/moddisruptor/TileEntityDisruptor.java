package com.olhcim.moddisruptor;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

public class TileEntityDisruptor extends TileEntity  implements ITickable {

    public TileEntityDisruptor() {
        register();
    }

    public void register() {
        RegistryDisruptors.register(this);
    }

    public void unregister() {
        RegistryDisruptors.unregister(this);
    }

    @Override
    public void onChunkUnload() {
        unregister();
    }

    public boolean isNear(EntityEnderman e) {
        return e.getDistanceSqToCenter(this.getPos()) <= BlockDisruptor.rangeSq && e.worldObj == this.worldObj;
    }

    public void update() {
        if(this.getWorld().isRemote) {
            BlockPos pos = this.getPos();

            if(this.getWorld().isBlockPowered(pos)) {

                for (int i = 0; i < BlockDisruptor.range * 4; i++) {
                    float x = pos.getX();
                    float y = pos.getY();
                    float z = pos.getZ();

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

                    this.getWorld().spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x + ox, y + oy, z + oz, 0, 0, 0, 0);
                }
            }
        }
    }

    private float random(float a, float b) {
        return (float)Math.random() * (b - a) + a;
    }
}