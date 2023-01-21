package org.fhtw.weatherX.client;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Controller
public class OpenWeatherConfigReader {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        File parentFile = new File(".");
        String parentPath = null;
        try {
            parentPath = parentFile.getCanonicalPath();
            System.out.println(parentPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



            //Properties prop=getProperties(propertiesPath+"/app.config");
            //System.out.println(prop.getProperty("openweather_api_key"));

    }

    public Properties getProperties(String configPath) throws FileNotFoundException, IOException {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(configPath);
        prop.load(fis);

        return prop;
    }
}
