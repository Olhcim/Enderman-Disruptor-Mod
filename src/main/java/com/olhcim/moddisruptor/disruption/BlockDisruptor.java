package com.olhcim.moddisruptor.disruption;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockDisruptor extends MyBlock implements ITileEntityProvider {
    public static final int range = 16;
    public static final int rangeSq = range * range;

    public BlockDisruptor() {
        super("disruptor", Material.sponge);

        setStepSound(Block.soundTypeMetal);
        setHardness(0.5f);
        setLightLevel(0.5f);
        setResistance(2000.0F);
        setHarvestLevel("pickaxe", 0);

        GameRegistry.registerTileEntity(TileEntityDisruptor.class, "tileEntityDisruptor");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int dat) {
        return new TileEntityDisruptor();
    }

    public void initRecipe() {
        GameRegistry.addShapedRecipe(new ItemStack(this),
                "IBI",
                "BEB",
                "ITI",

                'I', Blocks.iron_block,
                'B', Items.enchanted_book,
                'E', Items.ender_eye,
                'T', Blocks.enchanting_table
        );
    }
}
