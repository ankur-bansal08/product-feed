package com.tradedoubler.productfeed.controller;

import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import com.tradedoubler.productfeed.service.FileUploadService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileUploadControllerTest {

    @Mock
    private FileUploadService fileUploadService;

    @InjectMocks
    private FileUploadController fileUploadController;

    @Test
    public void testUploadXMLFile_ValidFile() throws ValidationException, EmptyFileException, JAXBException {
        MultipartFile file = new MockMultipartFile("test.xml", "xmlContent".getBytes());
        when(fileUploadService.processAndSaveData(file)).thenReturn("File uploaded and validated successfully");

        ResponseEntity<String> response = fileUploadController.uploadXMLFile(file);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File uploaded and validated successfully", response.getBody());
    }

    @Test
    public void testUploadXMLFile_EmptyFile() throws ValidationException, EmptyFileException, JAXBException {
        MultipartFile file = new MockMultipartFile("test.xml", new byte[0]);
        when(fileUploadService.processAndSaveData(file)).thenReturn("File is empty.");

        ResponseEntity<String> response = fileUploadController.uploadXMLFile(file);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File is empty.", response.getBody());
    }

    @Test
    public void testUploadXMLFile_InvalidFile() throws ValidationException, EmptyFileException, JAXBException {
        MultipartFile file = new MockMultipartFile("test.xml", "invalidXmlContent".getBytes());
        when(fileUploadService.processAndSaveData(file)).thenThrow(new ValidationException("XML data is invalid."));

        ValidationException thrown = assertThrows(ValidationException.class, () -> fileUploadController.uploadXMLFile(file));
        assertEquals("XML data is invalid.", thrown.getMessage());
    }
}

