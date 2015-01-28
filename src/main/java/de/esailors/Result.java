package de.esailors;

class Result {
    public final boolean healthy;
    public final FailureCause failureCause;

    private Result(boolean healthy, FailureCause failureCause) {

        this.healthy = healthy;
        this.failureCause = failureCause;
    }

    static Result healthy() {

        return new Result(true, null);
    }

    static Result unhealthy(FailureCause failureCause) {

        return new Result(false, failureCause);
    }
}
