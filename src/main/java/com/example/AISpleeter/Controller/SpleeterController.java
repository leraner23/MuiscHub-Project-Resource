package com.example.AISpleeter.Controller;

import com.example.AISpleeter.Service.SpleeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@RestController
@RequestMapping("/api/spleeter")
public class SpleeterController {

    @Autowired
    private SpleeterService spleeterService;

    @PostMapping("/split")
    public ResponseEntity<?> splitAudio(@RequestParam("file") MultipartFile file) {
        try {
            Path tempDir = Files.createTempDirectory("spleeter_upload");
            Path inputFile = tempDir.resolve(file.getOriginalFilename());
            file.transferTo(inputFile);

            Path resultDir = spleeterService.separate(inputFile.toString());
            Path vocals = resultDir.resolve("vocals.wav");

            if (!Files.exists(vocals)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: vocals.wav not found after processing.");
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new FileSystemResource(vocals.toFile()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

