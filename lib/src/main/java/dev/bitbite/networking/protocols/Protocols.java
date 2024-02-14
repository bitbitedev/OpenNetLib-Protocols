package dev.bitbite.networking.protocols;

import dev.bitbite.networking.protocols.http.HTTP;

/**
 * The Protocols class provides a collection of protocol classes.
 */
public class Protocols {

    /**
     * Private constructor to prevent instantiation.
     */
    private Protocols() {}

    /**
     * Represents the HTTP protocol class.
     */
    public static final Class<? extends Protocol> HTTP = HTTP.class;
}
