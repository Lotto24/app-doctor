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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class AppDoctorTest {

    private static final String[] NO_ARGS = new String[]{};

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {

        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {

        System.setOut(null);
    }

    @Test
    public void should_print_help_if_called_with_help_long_parameter() {

        AppDoctor.main(new String[]{"--help"});

        assertThat(outContent.toString(), containsString("Usage: "));
    }

    @Test
    public void should_print_one_failure_identified_and_solution_message() {

        AppDoctor.main(new String[]{"--db", "\"src/test/resources/failure-causes.yaml\"", "--log", "\"src/test/resources/simple-pattern.log\""});

        String out = outContent.toString();
        assertThat(out, containsString("Scanning log file (src/test/resources/simple-pattern.log)"));
        assertThat(out, containsString("Go to the frontend module and execute mvn clean install Then restart the webshop"));
    }

    @Test
    public void should_print_success_message_if_no_failure_was_identified() {

        AppDoctor.main(new String[]{"--db", "\"src/test/resources/failure-causes.yaml\"", "--log", "\"src/test/resources/no-failures.log\""});

        String out = outContent.toString();
        assertThat(out, containsString("Scanning log file (src/test/resources/no-failures.log)"));
        assertThat(out, containsString("Your application looks healthy!"));
    }

}