package de.esailors;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DoctorTest {

    private FailureDatabase db;
    private List<FailureCause> causes;

    @Before
    public void setup() {

        causes = new ArrayList<>();
        db = new FailureDatabase() {
            @Override
            public List<FailureCause> getFailureCauses() {

                return causes;
            }
        };
    }

    @Test
    public void should_return_unhealthy_result_with_first_failure_cause_found() {

        causes.add(new FailureCause("name", "description", ".*ClassNotFoundException.*SovereignStateLoaderListener", "solution"));

        Doctor doctor = new Doctor(db);
        Result result = doctor.diagnoseLogFile("src/test/resources/simple-pattern.log");
        assertThat(result.healthy, is(false));
        assertThat(result.failureCause.name, equalTo("name"));
        assertThat(result.failureCause.description, equalTo("description"));
        assertThat(result.failureCause.solution, equalTo("solution"));
    }
}
