package org.laxio.server;

import org.laxio.Application;
import org.laxio.listener.LaxioListenerManager;
import org.laxio.listener.ListenerManager;
import org.laxio.network.Network;
import org.laxio.network.NetworkServer;
import org.laxio.protocol.ProtocolRegistry;
import org.laxio.protocol.netty.protocol.BasicProtocolRegistry;
import org.laxio.server.network.NetworkServerBuilder;
import org.laxio.thread.LaxioThreadGroup;

import java.net.InetSocketAddress;

public class LaxioServerApplication implements ServerApplication {

    private final String name;
    private final LaxioThreadGroup threadGroup;
    private final NetworkServer network;
    private final ListenerManager listenerManager;
    private final ProtocolRegistry protocolRegistry;

    private final ServerConfiguration serverConfiguration;

    public LaxioServerApplication(String name, NetworkServerBuilder networkServerBuilder) {
        this.name = name;
        this.network = networkServerBuilder.build(this);
        this.threadGroup = new LaxioThreadGroup(this);
        this.listenerManager = new LaxioListenerManager(this);
        this.protocolRegistry = new BasicProtocolRegistry(this);
        this.serverConfiguration = new LaxioServerConfiguration();
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

    @Override
    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    @Override
    public ProtocolRegistry getProtocolRegistry() {
        return protocolRegistry;
    }

    @Override
    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }

    public static LaxioServerBuilder builder() {
        return new LaxioServerBuilder();
    }

}
