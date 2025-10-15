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
        OutputStream os = new FileOutputStream("src/test/resources/test-output.rng");
        //String outputFormat = "Compiled ODD";
        String outputFormat = "RelaxNG";
        //CustomizationSourceInputType customizationInputType = new CustomizationSourceInputType("mei401","MEI v4.0.1", "type_server-file", "source/mei-source_canonicalized.xml");
        //CustomizationSourceInputType sourceInputType = new CustomizationSourceInputType("mei401","MEI v4.0.1", "type_server-file", "source/mei-source_canonicalized.xml");
        //CustomizationSourceInputType sourceInputType = new CustomizationSourceInputType("meidev","MEI dev", "type_server-file", "source/mei-source_canonicalized.xml");
        //CustomizationSourceInputType customizationInputType = new CustomizationSourceInputType("meidev","MEI dev", "type_server-file", "source/mei-source_canonicalized.xml");
        CustomizationSourceInputType sourceInputType = new CustomizationSourceInputType("mei300","MEI 3.0.0", "type_server-file", "source/mei-source_canonicalized.xml");
        CustomizationSourceInputType customizationInputType = new CustomizationSourceInputType("c-mei-mensural", "MEI Mensural", CustomizationSourceInputType.TYPE_SERVER_FILE, "customizations/mei-Mensural.xml");
        List<CustomizationSourceInputType> sources = new ArrayList<>();
        sources.add(sourceInputType);
        List<CustomizationSourceInputType> customizations = new ArrayList<>();
        customizations.add(customizationInputType);
        String tempDir = "src/test/temp";
        customization.customize(null, sourceInputType, customizationInputType, outputFormat, os, null, null, tempDir);
        assertNotNull(new File("src/test/resources/test-output.rng"));
        System.out.println(new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.rng")), "UTF-8"));
        assertNotEquals("", new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.rng")), "UTF-8"));
        //Always differs:
        // <!--
        //Schema generated from ODD source 2024-04-10T09:12:30Z. .
        //--><!---->
        assertEquals("The files differ!",
                new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.rng"))).replaceAll("<!--[\\s\\S]*?-->",""),
                new String(Files.readAllBytes(Paths.get("src/test/resources/expected-output.rng"))).replaceAll("<!--[\\s\\S]*?-->", ""));
        outputFormat = "Compiled ODD";
        OutputStream os2 = new FileOutputStream("src/test/resources/test-output.odd");
        customization.customize(null, sourceInputType, customizationInputType, outputFormat, os2, null, null, tempDir);
        assertNotNull(new File("src/test/resources/test-output.odd"));
        assertNotEquals("", new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.odd")), "UTF-8"));
        //Always differs:
        // <!--
        //Schema generated from ODD source 2024-04-10T09:12:30Z. .
        //--><!---->
        assertEquals("The files differ!",
                new String(Files.readAllBytes(Paths.get("src/test/resources/test-output.odd"))).replaceAll("<!--[\\s\\S]*?-->",""),
                new String(Files.readAllBytes(Paths.get("src/test/resources/expected-output.odd"))).replaceAll("<!--[\\s\\S]*?-->", ""));
        os.close();
        os2.close();
    }

    @org.junit.Test
    public void getSupportedCustomizationSettings() {
        assertNotNull(customization.getSupportedCustomizationSettings());
        //for (CustomizationSetting cs : customization.getSupportedCustomizationSettings()) {
        //    System.out.println(cs.toString() + cs.getFormat() + cs.getOutputFormats());
        //}
    }
}