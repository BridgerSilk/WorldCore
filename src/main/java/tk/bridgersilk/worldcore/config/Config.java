package tk.bridgersilk.worldcore.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {
    private static Map<String, Object> config;

    @SuppressWarnings("unchecked")
    public static void load() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.yml")) {
            if (inputStream == null) {
                throw new RuntimeException("config.yml not found in resources!");
            }
            config = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.yml", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSection(String path) {
        String[] parts = path.split("\\.");
        Map<String, Object> current = config;
        for (String part : parts) {
            Object val = current.get(part);
            if (!(val instanceof Map)) {
                throw new RuntimeException("Invalid config section: " + path);
            }
            current = (Map<String, Object>) val;
        }
        return current;
    }

    public static Object get(String path) {
        String[] parts = path.split("\\.");
        Map<String, Object> current = config;

        for (int i = 0; i < parts.length - 1; i++) {
            Object val = current.get(parts[i]);
            if (!(val instanceof Map)) {
                throw new RuntimeException("Invalid config path: " + path);
            }
            current = (Map<String, Object>) val;
        }

        return current.get(parts[parts.length - 1]);
    }

    public static double getDouble(String path) {
        Object val = get(path);
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        }
        throw new RuntimeException("Config value at " + path + " is not a number");
    }
}
