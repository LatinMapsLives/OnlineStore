package by.demidov_a_r.onlinestore.service;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class ImageService {

    @Value("${app.image.path}")
    private String bucket;

    @SneakyThrows
    public void upload(String imageName, InputStream content){
        Path imagePath = Path.of(bucket, imageName);

        try (content){
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}
