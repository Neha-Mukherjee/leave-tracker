package com.practice.leavetracker.service;

import org.springframework.web.multipart.MultipartFile;

public interface MedicalDocumentService {
    String saveDocument(MultipartFile file);
}
