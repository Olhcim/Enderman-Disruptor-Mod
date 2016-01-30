package com.olhcim.moddisruptor;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Nathan on 2016/01/30.
 */
public class Config {

    private static final String VERSION = "0.1.0";

    private static final String GROUP_GENERAL = "General";
    private static final String VER_CHECK_NAME = "CheckVersion";
    private static final String VER_CHECK_DESC = "Must the mod check if the current version is the latest at launch, and print if one has been found when the games starts.";
    private static final boolean VCR_CHECK_DEFAULT = true;

    public static boolean checkVersion;

    public static void load(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile(), VERSION);
        checkVersion = config.getBoolean(VER_CHECK_NAME, GROUP_GENERAL, VCR_CHECK_DEFAULT, VER_CHECK_DESC);
        config.save();
    }
}
