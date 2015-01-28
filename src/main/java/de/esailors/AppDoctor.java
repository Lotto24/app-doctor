package de.esailors;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import java.util.ArrayList;
import java.util.List;

public class AppDoctor {

    private static final int ERROR_CODE = 1;

    public static void main(String[] args) {

        MainCommand main = new MainCommand();
        JCommander cmd = new JCommander(main);
        cmd.setProgramName(AppDoctor.class.getName());
        try {
            cmd.parse(args);
            if (main.help) {
                cmd.usage();
            } else {
                diagnose(main);
            }
        } catch (ParameterException ex) {
            cmd.usage();
        }
    }

    private static void diagnose(MainCommand main) {

        System.out.printf("Scanning log file (%s)%n", main.logPath);

        FailureDatabase db = new FailureDatabaseYaml(main.dbPath);
        Doctor doctor = new Doctor(db);
        Result result = doctor.diagnoseLogFile(main.logPath);
        if (result.healthy) {
            printSuccess();
        } else {
            printFailureCause(result.failureCause);
        }
    }

    private static void printSuccess() {

        System.out.println("Your application looks healthy!");
    }

    private static void printFailureCause(FailureCause cause) {

        System.out.println("A failure cause was identified");
        System.out.printf("Name: %s%n", cause.name);
        System.out.printf("Description: %s%n", cause.description);
        System.out.printf("Solution: %s%n", cause.solution);
    }

    static class MainCommand {

        @Parameter
        private List<String> parameters = new ArrayList<String>();

        @Parameter(names = "--log", description = "Path to the log file to be analysed")
        private String logPath;

        @Parameter(names = "--db", description = "Path to the yaml file containing the failure causes")
        private String dbPath;

        @Parameter(names = "--help", help = true)
        private boolean help;
    }

}


