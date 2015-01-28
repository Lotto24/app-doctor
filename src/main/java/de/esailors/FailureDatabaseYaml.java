package de.esailors;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class FailureDatabaseYaml implements FailureDatabase {

    private String filePath;
    private final List<FailureCause> causes = new ArrayList<>();

    public FailureDatabaseYaml(String path) {

        this.filePath = path;
        populateDatabaseFromYamlFile(path);
    }

    private void populateDatabaseFromYamlFile(String path) {

        Yaml yaml = new Yaml();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset())) {
            for (Object data : yaml.loadAll(reader)) {
                causes.add(createFailureCause(data));
            }
        } catch (IOException e) {
            Exceptions.<RuntimeException>doThrow(e);
        }
    }

    private FailureCause createFailureCause(Object data) {

        Map map = (Map) data;
        String name = (String) map.get("name");
        String description = (String) map.get("description");
        String pattern = (String) map.get("pattern");
        String solution = (String) map.get("solution");

        return new FailureCause(name, description, pattern, solution);
    }

    public List<FailureCause> getFailureCauses() {

        return Collections.unmodifiableList(causes);
    }
}
