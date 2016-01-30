package com.olhcim.moddisruptor.util;

import com.olhcim.moddisruptor.ModDisruptor;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import java.util.List;

public class VersionCheck {

    private static final String URL = "https://raw.githubusercontent.com/Olhcim/Enderman-Disruptor-Mod/versions/versions.txt";
    private static final String GITHUB_ERROR_404 = "400: Invalid request";
    private static final String GITHUB_ERROR_NOT_FOUND = "Not Found";
    private static boolean hasFinishedChecking = false;
    private static boolean hasChecked = false;
    private static boolean isNewerVersion = false;
    private static String latestVersion;

    private VersionCheck() {}

    /**
     * Returns An appropriate message based on the current state of the class.
     * @return An appropriate message.
     */
    public static String getMessage() {
        if(!hasChecked) {
            return "Version checking has not yet been initiated.";
        } else if (!hasFinishedChecking) {
            return "Version checking has been initiated but has not yet finished.";
        } else {
            if(latestVersion == null) {
                return "Version check failed! You'll have to check manually.";
            } else {
                if(!isNewerVersion) {
                    return "You have the latest version!";
                } else {
                    return "The newer version " + latestVersion+ " is available!";
                }
            }
        }
    }

    /**
     * Initiate a version check.
     * Register an event for when the player joins world.
     */
    public static void registerAndCheck() {
        if(!hasChecked) {
            MinecraftForge.EVENT_BUS.register( new VersionCheck() );

            hasChecked = true;

            FileActionDownload downloader = new FileActionDownload(URL) {
                @Override
                public void downloaded(List<String> lines) {
                    parse(ModDisruptor.VERSION, lines);
                }
            };

            downloader.start();
        } else {
            System.out.println("Already Checked Version.");
        }
    }

    /**
     * Private method to parse the version file lines and update the static class
     * @param currentVersion The current version for determining if the other versions are newer.
     * @param lines The lines in the version file.
     */
    private static void parse(String currentVersion, List<String> lines) {
        if(lines != null) {
            if( !lines.contains(GITHUB_ERROR_404) && !lines.contains(GITHUB_ERROR_NOT_FOUND) ) { //if url error
                latestVersion = currentVersion;
                isNewerVersion = false;
                for (String line : lines) {
                    String v = line.trim();
                    if(v.trim().compareTo(latestVersion) > 0) {
                        isNewerVersion = true;
                        latestVersion = v;
                    }
                }
            }
        }
        hasFinishedChecking = true;
        System.out.println(getMessage());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        if(hasChecked && hasFinishedChecking)
            event.player.addChatComponentMessage( new ChatComponentText( "[Enderman Disruptor Mod] " + getMessage()) );
    }
}