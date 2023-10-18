package com.tradedoubler.productfeed.service;

import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import jakarta.xml.bind.JAXBException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     *  Validate and process the uploaded file
     * @param file
     * @return
     * @throws ValidationException
     * @throws EmptyFileException
     * @throws JAXBException
     */
    String processAndSaveData(MultipartFile file) throws ValidationException, EmptyFileException, JAXBException;
}
