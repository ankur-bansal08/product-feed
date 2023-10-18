package com.tradedoubler.productfeed.validator;

import com.tradedoubler.pf.model.xml.output.Result;
import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class XMLFileUploadValidatorTest {

    @InjectMocks
    private XMLFileUploadValidator xmlFileUploadValidator;

    @Value("classpath:feed/Test_Products.xml")
    Resource resourceFile;

    @Value("classpath:feed/InValid_Test_Products.xml")
    Resource inValidResourceFile;

    @Value("classpath:feed/NoProducts.xml")
    Resource noProductResourceFile;

    @Mock
    private MultipartFile file;

    @Test
    public void testValidateXml_ValidXml() throws IOException, ValidationException, EmptyFileException {
        file = new MockMultipartFile("file.xml", resourceFile.getContentAsByteArray());
        Result xmlData = xmlFileUploadValidator.validateXml(file);
        BigDecimal expectedVersion = new BigDecimal("3.0");
        assertNotNull(xmlData);
        assertEquals(expectedVersion, xmlData.getVersion());
    }

    @Test
    public void testValidateXml_EmptyFile() {
        when(file.isEmpty()).thenReturn(true);
        assertThrows(EmptyFileException.class, () -> xmlFileUploadValidator.validateXml(file));
    }

    @Test
    public void testValidateXml_InvalidXml() throws IOException {
        file = new MockMultipartFile("file.xml", inValidResourceFile.getContentAsByteArray());
        assertThrows(ValidationException.class, () -> xmlFileUploadValidator.validateXml(file));
    }

    @Test
    public void testValidateXml_InvalidXmlData() throws IOException {
        String errorMessage = "XML data is invalid.";
        file = new MockMultipartFile("file.xml", noProductResourceFile.getContentAsByteArray());
        ValidationException thrown = assertThrows(ValidationException.class, () -> xmlFileUploadValidator.validateXml(file));
        assertTrue(thrown.getMessage().contains(errorMessage));
    }

}

