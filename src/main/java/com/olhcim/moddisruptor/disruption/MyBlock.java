package com.olhcim.moddisruptor.disruption;

import com.olhcim.moddisruptor.ModDisruptor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public abstract class MyBlock extends Block {

    private final String name;
    private final String resourceLocation;

    public MyBlock(String name, Material material) {
        super(material);
        this.name = name;
        this.resourceLocation = ModDisruptor.MODID + ":" + name;

        setUnlocalizedName(ModDisruptor.MODID + "_" + this.name);
        setCreativeTab(ModDisruptor.creativeTab);

        GameRegistry.registerBlock(this, this.name);
    }

    public String getName() {
        return name;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public void registerRecipe(Object... params) {
        registerRecipe(1, params);
    }

    public void registerRecipe(int amount, Object... params) {
        GameRegistry.addRecipe(new ItemStack(this, amount), params);
    }

    public final void init(FMLInitializationEvent event) {
        initRecipe();

        if (event.getSide() == Side.CLIENT) {
            Minecraft.getMinecraft()
                    .getRenderItem()
                    .getItemModelMesher()
                    .register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getResourceLocation()));
        }
    }

    abstract void initRecipe();
}
