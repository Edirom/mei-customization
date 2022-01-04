package de.edirom.meigarage.mei;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MEICustomizationTest {

    MEICustomization instance;

    @Before
    public void setUp() throws Exception {
        instance = new MEICustomization();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void error() {
    }

    @Test
    public void fatalError() {
    }

    @Test
    public void warning() {
    }

    @Test
    public void customize() {
    }

    @Test
    public void getSupportedCustomizationSettings() throws Exception{
        assertNotNull(instance.getSupportedCustomizationSettings());
    }
}