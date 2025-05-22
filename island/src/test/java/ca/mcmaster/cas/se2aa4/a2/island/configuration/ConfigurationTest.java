package ca.mcmaster.cas.se2aa4.a2.island.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationTest {
    @Test
    public void testParsing(){
        String[] args = new String[]{
                "-i", "input.mesh",
                "-o", "output.mesh",
                "-s", "circle",
                "-a", "artic",
                "-l", "10",
                "-r", "5",
                "-q", "6",
                "-c", "7",
                "-b", "tropical",
                "-d", "10000",
                "-m", "altitude"
        };

        Configuration configuration = new Configuration(args);
        assertEquals("input.mesh", configuration.export(Configuration.INPUT_FILENAME));
        assertEquals("output.mesh", configuration.export(Configuration.OUTPUT_FILENAME));
        assertEquals("circle", configuration.export(Configuration.SHAPE));
        assertEquals("artic", configuration.export(Configuration.ALTITUDE));
        assertEquals("10", configuration.export(Configuration.LAKES));
        assertEquals("5", configuration.export(Configuration.RIVERS));
        assertEquals("6", configuration.export(Configuration.AQUIFERS));
        assertEquals("7", configuration.export(Configuration.SOIL));
        assertEquals("tropical", configuration.export(Configuration.BIOMES));
        assertEquals("10000", configuration.export(Configuration.SEED));
        assertEquals("altitude", configuration.export(Configuration.HEATMAP));

    }
}
