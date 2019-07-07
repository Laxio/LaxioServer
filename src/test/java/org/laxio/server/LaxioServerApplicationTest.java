package org.laxio.server;

import org.junit.jupiter.api.Test;
import org.laxio.LaxioApplication;
import org.laxio.protocol.netty.server.LaxioServerNettyServer;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

class LaxioServerApplicationTest {

    @Test
    void createServer() {
        LaxioApplication application = new LaxioServerApplication("test", app -> new LaxioServerNettyServer(app, new InetSocketAddress("0.0.0.0", 25565)));
        application.start();
    }

}
