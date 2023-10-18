package com.tradedoubler.productfeed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradedoubler.pf.model.xml.output.Result;
import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import com.tradedoubler.productfeed.mapper.CustomObjectMapper;
import com.tradedoubler.productfeed.repository.ProductRepository;
import com.tradedoubler.productfeed.validator.FileUploadValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class XMLFileUploadServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FileUploadValidator fileUploadValidator;

    @Mock
    private CustomObjectMapper customObjectMapper;

    @InjectMocks
    private XMLFileUploadServiceImpl xmlFileUploadService;

    @Test
    public void testProcessAndSaveData_ValidFile() throws Exception {
        // Arrange
        MultipartFile file = new MockMultipartFile("test.xml", "xmlContent".getBytes());

        Result xmlData = createSampleResult(); // Create a sample Result object for testing

        when(fileUploadValidator.validateXml(file)).thenReturn(xmlData);
        // Mock the behavior of CustomObjectMapper to return a valid ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        when(customObjectMapper.objectMapper()).thenReturn(objectMapper);
        when(productRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        // Act
        String result = xmlFileUploadService.processAndSaveData(file);

        // Assert
        assertEquals("File uploaded and validated successfully.", result);
    }

    @Test
    public void testProcessAndSaveData_EmptyFile() throws Exception {
        MultipartFile file = new MockMultipartFile("test.xml", new byte[0]);
        when(fileUploadValidator.validateXml(file)).thenThrow(new EmptyFileException("File is empty."));
        assertThrows(EmptyFileException.class, () -> xmlFileUploadService.processAndSaveData(file));
    }

    @Test
    public void testProcessAndSaveData_InvalidXml() throws Exception {
        // Arrange
        MultipartFile file = new MockMultipartFile("test.xml", "invalidXmlContent".getBytes());
        when(fileUploadValidator.validateXml(file)).thenThrow(new ValidationException("XML data is invalid."));
        assertThrows(ValidationException.class, () -> xmlFileUploadService.processAndSaveData(file));
    }

    private Result createSampleResult() {
        Result result = new Result();
        result.setProducts(new Result.Products());
        Result.Products.Product product = new Result.Products.Product();
        result.getProducts().getProducts().add(product);
        return result;
    }
}
