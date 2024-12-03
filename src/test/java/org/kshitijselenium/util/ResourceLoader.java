package org.kshitijselenium.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {

    private static final Logger log= LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path) throws IOException {
        log.info("reading resource from location : {}",path);
        InputStream stream=ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        //Here I am trying to load json file which present in classpath i.e. 'target' directory
        if(Objects.nonNull(stream)) //if stream is not null than return the stream
        {
            return stream;
        }
        return Files.newInputStream(Path.of(path)); //Here we are trying to check if path provided is present
        //in normal project directory i.e. file system
    }
}
