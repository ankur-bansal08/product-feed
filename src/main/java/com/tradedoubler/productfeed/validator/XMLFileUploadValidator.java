package com.tradedoubler.productfeed.validator;

import com.tradedoubler.pf.model.xml.output.Result;
import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class XMLFileUploadValidator implements FileUploadValidator {

    @Override
    public Result validateXml(MultipartFile file) throws EmptyFileException, ValidationException {
        try {
            if (file.isEmpty()) {
                log.error("File {} is empty.", file.getName());
                throw new EmptyFileException(String.format("File %s is empty.", file.getName()));
            }
            Result xmlData = unMarshalResult(file);
            // Perform additional validation if needed
            if (xmlData.getProducts() == null) {
                throw new ValidationException("XML data is invalid.");
            }
            return xmlData;
        } catch (JAXBException e) {
            String errorMessage = StringUtils.isEmpty(e.getErrorCode()) && Objects.nonNull(e.getLinkedException())
                    ? "Error validating XML: " + e.getLinkedException().getMessage()
                    : "Error validating XML: " + e.getMessage();
            log.error(errorMessage);
            throw new ValidationException(errorMessage, e);
        } catch (IOException e) {
            log.error("Error Reading File: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static Result unMarshalResult(MultipartFile file) throws JAXBException, IOException {
        //String xmlContent = new String(file.getBytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(Result.class);
        //try (StringReader stringReader = new StringReader(xmlContent)) {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Result) unmarshaller.unmarshal(file.getInputStream());
    }
}
