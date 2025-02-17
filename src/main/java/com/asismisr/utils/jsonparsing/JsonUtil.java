package com.asismisr.utils.jsonparsing;

import com.asismisr.exceptions.FrameworkExceptions;
import com.asismisr.exceptions.ResourceNotFoundExceptions;
import com.asismisr.utils.resourceloader.ResourceLoader;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type) {
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }catch (FileNotFoundException e){
            throw new ResourceNotFoundExceptions("unable to read test data {}"+ path+ e);
        } catch (Exception e) {
            throw new FrameworkExceptions("Some issue in reading the excel file content with path:"+ path);
        }
    }


}