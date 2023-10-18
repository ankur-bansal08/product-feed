package com.tradedoubler.productfeed.controller;

import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import com.tradedoubler.productfeed.service.FileUploadService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/xml")
    public ResponseEntity<String> uploadXMLFile(@RequestParam("file") MultipartFile file) throws ValidationException, EmptyFileException, JAXBException {
        String result = fileUploadService.processAndSaveData(file);
        return ResponseEntity.ok(result);
    }
}


