package de.edirom.meigarage.mei;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.psnc.dl.ege.configuration.EGEConfigurationManager;
import pl.psnc.dl.ege.exception.ConverterException;
import pl.psnc.dl.ege.exception.EGEException;
import pl.psnc.dl.ege.types.ConversionActionArguments;
import pl.psnc.dl.ege.types.CustomizationSetting;
import pl.psnc.dl.ege.types.CustomizationSourceInputType;
import pl.psnc.dl.ege.types.DataType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MEICustomizationTest {
    private MEICustomization customization;

    @org.junit.Before
    public void setUp() throws Exception {
        customization = new MEICustomization();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        customization = null;
    }

    @org.junit.Test
    public void customize() throws IOException, EGEException {
        OutputStream os = new FileOutputStream("src/test/resources/test-output.odd");
        String outputFormat = "Compiled ODD";
        CustomizationSourceInputType sourceInputType = new CustomizationSourceInputType("mei401","MEI v4.0.1", "type_server-file", "source/mei-source_canonicalized.xml");
        CustomizationSourceInputType customizationInputType = new CustomizationSourceInputType("mei401","MEI v4.0.1", "type_server-file", "source/mei-source_canonicalized.xml");
        List<CustomizationSourceInputType> sources = new ArrayList<>();
        sources.add(sourceInputType);
        List<CustomizationSourceInputType> customizations = new ArrayList<>();
        customizations.add(customizationInputType);
        List<String> outputFormats = new ArrayList<>();
        outputFormats.add(outputFormat);
        String tempDir = "src/test/temp";
        customization.customize(null, sourceInputType, customizationInputType, outputFormat, os, null, null, tempDir);
        assertNotNull(new File("src/test/resources/test-output.odd"));
        //System.out.println(new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.odd")), "UTF-8"));
        assertNotEquals("", new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.odd")), "UTF-8"));
        os.close();
    }

    @org.junit.Test
    public void getSupportedCustomizationSettings() {
        assertNotNull(customization.getSupportedCustomizationSettings());
        //for (CustomizationSetting cs : customization.getSupportedCustomizationSettings()) {
        //    System.out.println(cs.toString() + cs.getFormat() + cs.getOutputFormats());
        //}
    }
}