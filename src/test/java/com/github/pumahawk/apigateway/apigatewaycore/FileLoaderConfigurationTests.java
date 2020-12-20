package com.github.pumahawk.apigateway.apigatewaycore;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.github.pumahawk.apigateway.apigatewaycore.configurations.FileLoaderConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.SourceConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.FileLoaderConfiguration.PropertyConfigurations;

import org.junit.jupiter.api.Test;

public class FileLoaderConfigurationTests {

    public String getFilePath(String file) {
        return "src/test/resources/configuration/json/" + file;
    }

    public void testStreamContent(InputStream s, String c) {

        try (Scanner sc = new Scanner(s)) {
            String i;
            for (i = ""; sc.hasNextLine();) {
                if (!i.equals("")) {
                    i += "\n";
                }
                i += sc.nextLine();
            }
    
            assertEquals(c, i);
        }

    }

    @Test
    public void loadSimpleFileTest() {
        String path = getFilePath("test1.json");
        File f = new File(path);
        assertTrue(f.exists());
        PropertyConfigurations prconf = new PropertyConfigurations();
        prconf.setLocation(path);
        List<SourceConfiguration> fl = new FileLoaderConfiguration().getSourceConfigurations(prconf);

        Iterator<SourceConfiguration> iterator = fl.iterator();
        assertTrue(iterator.hasNext());
        SourceConfiguration c = iterator.next();
        assertFalse(iterator.hasNext());
        assertEquals("json", c.type());
        
        testStreamContent(c.getStream(), "{\"uri\": \"http://www.localhost:8080\"}");
    }
    
    @Test
    public void loadFolderFileTest() {
        String path = getFilePath("test2");
        File f = new File(path);
        assertTrue(f.exists());

        
        PropertyConfigurations prconf = new PropertyConfigurations();
        prconf.setLocation(path);
        List<SourceConfiguration> fl = new FileLoaderConfiguration().getSourceConfigurations(prconf);

        Iterator<SourceConfiguration> iterator = fl.iterator();
        assertTrue(iterator.hasNext());

        SourceConfiguration c1 = iterator.next();
        assertEquals("json", c1.type());
        
        assertTrue(iterator.hasNext());

        SourceConfiguration c2 = iterator.next();
        assertEquals("json", c2.type());

        assertFalse(iterator.hasNext());
        
        testStreamContent(c1.getStream(), "{\"uri\": \"http://www.localhost.t:8080\"}");
        testStreamContent(c2.getStream(), "{\"uri\": \"http://www.localhost.t:8080\"}");
    }
}
