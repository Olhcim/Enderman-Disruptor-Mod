package com.olhcim.moddisruptor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FileActionDownload {

    private Thread thread;

    /**
     * Downloads a file and passes it to the Overriden method.
     * Not intended to be used repeatedly.
     * @param url A full url, not just the domain.
     */
    public FileActionDownload(final String url) {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> lines = fetch(url);
                downloaded(lines);
            }
        });
    }

    /**
     * Starts the file download
     * @return this
     */
    public FileActionDownload start() {
        thread.start();
        return this;
    }

    /**
     *  Called when the thread finishes downloading the file.
     */
    public abstract void downloaded(List<String> lines);

    /**
     * Returns the lines in a file at the specified url.
     * Returns null if there is an error.
     * @param url A full url, not just the domain.
     * @return A String array, or null.
     */
    public static List<String> fetch(String url) {
        try {
            List<String> lines = new ArrayList<String>();
            URL u = new URL(url);

            InputStream is = u.openStream();
            InputStreamReader isr = new InputStreamReader( is );
            BufferedReader in = new BufferedReader(isr);

            String line;
            while ( (line = in.readLine()) != null ) {
                lines.add(line);
            }
            in.close();

            return lines;
        } catch (IOException ignored) {
            return null;
        }
    }
}


