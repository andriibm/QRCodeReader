package org.abez.qrreader.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile implements PropertiesSource {
    private static final String PROP_FILE_NAME = "app.properties";

    @Override
    public String getProperty(String key) {
        Properties props = new Properties();

        try (InputStream inputStream = PropertiesFile.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME)) {
            if (inputStream == null) {
                throw new FileNotFoundException(String.format("Properties file '%s' is missing.", PROP_FILE_NAME));
            }
            props.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return props.getProperty(key);
    }
}
