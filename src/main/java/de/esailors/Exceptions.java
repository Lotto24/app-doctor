package de.esailors;

final class Exceptions {

    private Exceptions() {

    }

    @SuppressWarnings("unchecked")
    static <E extends Exception> void doThrow(Exception e) throws E {

        throw (E) e;
    }
}
