package de.esailors;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

public class FailureDatabaseYamlTest {

    @Test
    public void should_parse_failure_database_from_yaml_file() {

        FailureDatabaseYaml db = new FailureDatabaseYaml("src/test/resources/failure-causes.yaml");

        assertThat(db.getFailureCauses(), hasSize(2));

        assertThat(db.getFailureCauses().get(0).name, equalTo("ClassNotFoundException-SovereignStateLoaderListener"));
        assertThat(db.getFailureCauses().get(0).description, equalTo("Strange failure. It happens sometimes due to maven concurrent runs."));
        assertThat(db.getFailureCauses().get(0).patternString, equalTo(".*ClassNotFoundException.*SovereignStateLoaderListener"));
        assertThat(db.getFailureCauses().get(0).solution, equalTo("Go to the frontend module and execute mvn clean install Then restart the " +
                "webshop"));

        assertThat(db.getFailureCauses().get(1).name, equalTo("dummy"));
        assertThat(db.getFailureCauses().get(1).description, equalTo("Dummy failure description for tests"));
        assertThat(db.getFailureCauses().get(1).patternString, equalTo(".*dummy.*"));
        assertThat(db.getFailureCauses().get(1).solution, equalTo("There is no solution for this dummy failure"));
    }
}