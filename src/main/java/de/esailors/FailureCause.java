package de.esailors;

import java.util.regex.Pattern;

class FailureCause {
    public final String name;
    public final String description;
    public final String patternString;
    public final String solution;
    public final Pattern pattern;

    public FailureCause(String name, String description, String pattern, String solution) {

        this.name = name;
        this.description = description;
        this.patternString = pattern;
        this.solution = solution;
        this.pattern = Pattern.compile(pattern);
    }
}
