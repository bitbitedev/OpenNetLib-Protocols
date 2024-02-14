package dev.bitbite.networking.protocols;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProtocolTest {

    static Server server;

    @BeforeAll
    static void setup() throws Exception{
        server = new Server(Protocols.HTTP, 8080){
            @Override
            public Response handleRequest(Request request, Response response) {
                response.setData("Hello World");
                return response;
            }
        };
    }

    @Test
    void testServer() {
        assertDoesNotThrow(() -> server.start());
    }

    @AfterAll
    static void tearDown() {
        server.close();
    }
}
