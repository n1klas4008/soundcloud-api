package com.n1klas4008.data.media.download;

import com.n1klas4008.data.media.hydratable.impl.track.Track;
import com.n1klas4008.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;



public class FileManager {

    public static Path path;

    static {
        try {
            setup(Paths.get(System.getProperty("user.home")).resolve("soundcloud-dl"));
        } catch (IOException e) {
            Logger.error(e);
        }
        Logger.info("Path: {}", FileManager.path.toString());
    }

    public static void setup(Path path) throws IOException {
        Files.createDirectories(path);
        FileManager.path = path;
    }

    public static File[] getCache() {
        return path.toFile().listFiles();
    }

    public static File getFile(Track track) {
        String filename = String.format("%s.%s.mp3", track.getUser().getPermalink(), track.getPermalink());
        return path.resolve(filename).toFile();
    }

    public static boolean isCached(Track track) {
        String filename = String.format("%s.%s.mp3", track.getUser().getPermalink(), track.getPermalink());
        return path.resolve(filename).toFile().exists();
    }

    public static CompletableFuture<File> store(Track track, byte[] b) {
        return CompletableFuture.supplyAsync(() -> {
            String filename = String.format("%s.%s.mp3", track.getUser().getPermalink(), track.getPermalink());
            File file = FileManager.path.resolve(filename).toFile();
            try (FileOutputStream stream = new FileOutputStream(file)) {
                stream.write(b);
                stream.flush();
            } catch (IOException e) {
                Logger.error(e);
            }
            return file;
        });
    }

}
