package ca.mcmaster.cas.se2aa4.a2.island.configuration;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    public static final String INPUT_FILENAME = "i";
    public static final String OUTPUT_FILENAME = "o";
    public static final String HELP = "help";
    public static final String SHAPE = "s";
    public static final String ALTITUDE = "a";
    public static final String LAKES = "l";
    public static final String RIVERS = "r"; // TODO
    public static final String AQUIFERS = "q";
    public static final String SOIL = "c"; // TODO
    public static final String BIOMES = "b"; // TODO
    public static final String SEED = "d";
    public static final String HEATMAP = "m";
    public static final String CITIES = "t";

    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
            if (cli.hasOption(HELP)) {
                help();
            }
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for (Option o : cli.getOptions()) {
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    public String export(String key) {
        return cli.getOptionValue(key);
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT_FILENAME, true, "Input file name"));
        options.addOption(new Option(OUTPUT_FILENAME, true, "Output file name"));
        options.addOption(new Option(SHAPE, true, "Shape of the island"));
        options.addOption(new Option(ALTITUDE, true, "Altitude of the island"));
        options.addOption(new Option(LAKES, true, "The maximum number of lakes"));
        options.addOption(new Option(RIVERS, true, "The number of rivers"));
        options.addOption(new Option(AQUIFERS, true, "The number of aquifers"));
        options.addOption(new Option(SOIL, true, "The absorption profile of the soil"));
        options.addOption(new Option(BIOMES, true, "The biome of the island"));
        options.addOption(new Option(SEED, true, "The seed of the island"));
        options.addOption(new Option(HELP, false, "print help message"));
        options.addOption(new Option(HEATMAP, true, "Select the type of the heatmap"));
        options.addOption(new Option(CITIES, true, "The number of cities"));
        return options;
    }
}
