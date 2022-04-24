package ru.koda.gigachat.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;

/**
 * Вспомогательный класс для работы с файлами.
 */
public class FileIOHelper {

    public static String saveUploadedFile(final MultipartFile file, final String uploadFolder) throws IOException {
        if (!file.isEmpty()) {
            final byte[] bytes = file.getBytes();
            final String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            String fileExtension = "";
            if (split.length > 0) {
                fileExtension = "." + split[split.length - 1];
            }
            final String fileName = UUID.randomUUID() + fileExtension;
            final Path path = Paths.get(uploadFolder, fileName);
            Files.write(path, bytes, StandardOpenOption.CREATE);
            return fileName;
        }
        return null;
    }

    public static void deleteFile(String fileName, String folder) throws IOException {
        final Path path = Paths.get(folder, fileName);
        Files.delete(path);
    }

}
