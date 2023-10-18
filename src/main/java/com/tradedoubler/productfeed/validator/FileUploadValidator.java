package com.tradedoubler.productfeed.validator;

import com.tradedoubler.pf.model.xml.output.Result;
import com.tradedoubler.productfeed.Exception.EmptyFileException;
import com.tradedoubler.productfeed.Exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadValidator {

    Result validateXml(MultipartFile file) throws EmptyFileException, ValidationException;
}
