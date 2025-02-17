package com.asismisr.utils.resourceloader;

import com.asismisr.exceptions.ResourceNotFoundExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*
    A simple utility to read file.
    first we check the classpath. if found, it is used.
    if not, then we check the filesystem
 */
public class ResourceLoader {

    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path) {
        log.info("reading resource from location: {}", path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if(Objects.nonNull(stream)){
            return stream;
        }

        try {
            return Files.newInputStream(Path.of(path));
        } catch (IOException e) {
            throw new ResourceNotFoundExceptions("unable to read test data {}"+ path+ e);
        }
    }

}
