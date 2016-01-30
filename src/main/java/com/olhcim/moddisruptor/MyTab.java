package com.olhcim.moddisruptor;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MyTab extends CreativeTabs {

    private final Item iconItem;

    public MyTab(Block block) {
        this(Item.getItemFromBlock(block));
    }

    public MyTab(Item item) {
        super("tab_" + ModDisruptor.MODID);
        iconItem = item;
    }

    @Override
    public Item getTabIconItem() {
        return iconItem;
    }
}
