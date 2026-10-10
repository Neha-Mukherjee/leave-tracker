package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.service.MedicalDocumentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class MedicalDocumentServiceImpl implements MedicalDocumentService {
    private final Path uploadDirectory =
            Paths.get("uploads/medical-documents");

    @Override
    public String saveDocument(MultipartFile file) {
        try {

            Files.createDirectories(uploadDirectory);

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = uploadDirectory.resolve(fileName);

            Files.copy(file.getInputStream(), filePath);

            return filePath.toString();

        } catch (IOException exception) {
            throw new RuntimeException("Failed to save medical document");
        }
    }
}

