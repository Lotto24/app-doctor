package de.esailors;

import java.util.List;

/**
 * Database of failure causes.
 */
interface FailureDatabase {

    List<FailureCause> getFailureCauses();
}
