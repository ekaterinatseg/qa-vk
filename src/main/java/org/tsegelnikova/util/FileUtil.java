package org.tsegelnikova.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUtil {
    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static File getFileFromResource(String filename) {
        URL resource = FileUtil.class.getClassLoader().getResource(filename);
        File file;

        try {
            assert resource != null;
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static File downloadFile(String url) {
        try(InputStream in = new URL(url).openStream()) {
            Path temp = Files.createTempFile("im" + UUID.randomUUID(), ".jpeg");
            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
            return temp.toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
