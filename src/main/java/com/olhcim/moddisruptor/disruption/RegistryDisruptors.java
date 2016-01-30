package com.olhcim.moddisruptor.disruption;

import java.util.ArrayList;

import com.olhcim.moddisruptor.ModDisruptor;
import net.minecraft.entity.monster.EntityEnderman;


public class RegistryDisruptors {

    private static ArrayList<TileEntityDisruptor> disruptors = new ArrayList<TileEntityDisruptor>();

    public static void register(TileEntityDisruptor te) {
        disruptors.add(te);
    }

    public static void unregister(TileEntityDisruptor te) {
        disruptors.remove(te);
    }

    public static boolean isEntityNear(EntityEnderman e) {
        for (TileEntityDisruptor te : new ArrayList<TileEntityDisruptor>(disruptors)) {
            if (te.isNear(e)) {
                if (validate(te)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validate(TileEntityDisruptor te) {
        if (te.isInvalid() || te.getWorld().getBlockState(te.getPos()).getBlock() != ModDisruptor.blockDisruptor) {
            RegistryDisruptors.unregister(te);
            return false;
        }
        return true;
    }
}
