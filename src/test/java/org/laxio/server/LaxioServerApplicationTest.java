package org.laxio.server;

import org.junit.jupiter.api.Test;
import org.laxio.Application;
import org.laxio.protocol.netty.server.LaxioServerNettyServer;

import java.net.InetSocketAddress;

class LaxioServerApplicationTest {

    @Test
    void createServer() {
        Application application = new LaxioServerApplication("test", app -> new LaxioServerNettyServer(app, new InetSocketAddress("0.0.0.0", 25565)));
        application.start();
    }

}
