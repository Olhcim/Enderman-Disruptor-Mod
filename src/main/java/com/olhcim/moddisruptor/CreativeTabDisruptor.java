package com.olhcim.moddisruptor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabDisruptor extends CreativeTabs {

    public CreativeTabDisruptor() {
        super("tab_" + ModDisruptor.MODID);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(ModDisruptor.blockDisruptor);
    }
}
