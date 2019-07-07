package org.laxio.server;

import org.laxio.LaxioApplication;
import org.laxio.network.Network;
import org.laxio.network.NetworkServer;
import org.laxio.server.network.NetworkServerBuilder;
import org.laxio.thread.LaxioThreadGroup;

import java.net.InetSocketAddress;

public class LaxioServerApplication implements LaxioApplication {

    private final String name;
    private final LaxioThreadGroup threadGroup;
    private final NetworkServer network;

    public LaxioServerApplication(String name, NetworkServerBuilder networkServerBuilder) {
        this.name = name;
        this.network = networkServerBuilder.build(this);
        this.threadGroup = new LaxioThreadGroup(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public InetSocketAddress getAddress() {
        return network.getBindAddress();
    }

    @Override
    public void start() {
        network.start();
    }

    @Override
    public boolean isRunning() {
        return network.isRunning();
    }

    @Override
    public void stop() {
        network.shutdown();
    }

    public static LaxioServerBuilder builder() {
        return new LaxioServerBuilder();
    }

}
