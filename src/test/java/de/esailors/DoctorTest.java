/*
 * Copyright (c) 2015 eSailors IT Solutions GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
