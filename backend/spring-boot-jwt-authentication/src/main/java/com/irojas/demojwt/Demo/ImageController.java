package com.irojas.demojwt.Demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageController {
    private final String uploadDir = "C:/ImagenStreaming"; // Directorio donde se guardarán las imágenes

    public ImageController() {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Crea el directorio si no existe
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo está vacío.");
        }

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDir + fileName);
            file.transferTo(destinationFile); // Guarda el archivo en el servidor
            String imageUrl = "/api/images/" + fileName; // URL para acceder a la imagen
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> getImage(@PathVariable String fileName) {
        File file = new File(uploadDir + fileName);
        if (file.exists()) {
            return ResponseEntity.ok().body(file);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen no encontrada.");
        }
    }
}
