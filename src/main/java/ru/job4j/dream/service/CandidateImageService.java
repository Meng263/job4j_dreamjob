package ru.job4j.dream.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CandidateImageService {
    private final Path imageRoot = new File(System.getProperty("user.home")).toPath().resolve("candidate_images");

    private CandidateImageService() {
        imageRoot.toFile().mkdirs();
    }

    private static final CandidateImageService INST = new CandidateImageService();

    public static CandidateImageService instOf() {
        return INST;
    }

    public void saveImage(String fileName, InputStream item) throws IOException {
        Path imageFile = imageRoot.resolve(fileName + ".png");
        Files.write(imageFile, item.readAllBytes());
    }

    public byte[] getImageOrNull(String fileName) {
        Path image = imageRoot.resolve(fileName + ".png");
        if (Files.exists(image)) {
            try {
                return Files.readAllBytes(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void deleteImage(String fileName) {
        try {
            Files.delete(imageRoot.resolve(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
