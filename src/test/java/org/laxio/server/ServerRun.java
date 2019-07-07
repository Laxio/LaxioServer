package org.laxio.server;

import java.net.InetSocketAddress;

public class ServerRun {

    public static void main(String[] args) {
        String all = "0.0.0.0";
        createServer("TS1", new InetSocketAddress(all, 25565));
        createServer("TS2", new InetSocketAddress(all, 25566));
        createServer("TS3", new InetSocketAddress(all, 25567));
        createServer("TS4", new InetSocketAddress(all, 25568));
        createServer("TS5", new InetSocketAddress(all, 25569));
    }

    private static LaxioServerApplication createServer(String name, InetSocketAddress address) {
        LaxioServerApplication application = LaxioServerApplication.builder().name(name).address(address).build();
        application.start();
        return application;
    }

}
