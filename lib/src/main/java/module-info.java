
/**
 * This module defines the networking protocols for the OpenNetLib library.
 * It requires the OpenNetLib module and the Lombok library.
 */
module dev.bitbite.networking.protocols {
    exports dev.bitbite.networking.protocols;
    exports dev.bitbite.networking.protocols.http;

    requires transitive dev.bitbite.opennetlib;
    requires lombok;
}
