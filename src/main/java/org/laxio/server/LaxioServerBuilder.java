package org.laxio.server;

import org.laxio.Application;
import org.laxio.network.NetworkServer;
import org.laxio.protocol.netty.server.LaxioServerNettyServer;
import org.laxio.server.network.NetworkServerBuilder;
import org.laxio.util.Conditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class LaxioServerBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioServerBuilder.class);

    private String name;
    private InetSocketAddress address;
    private NetworkServerBuilder networkServerBuilder;

    LaxioServerBuilder() {
        // package access
        address = new InetSocketAddress("0.0.0.0", 25565);
        networkServerBuilder = new LaxioServerBuilderNetworkServerBuilder();
    }

    public LaxioServerBuilder name(String name) {
        Conditions.notNull(name, "name");
        this.name = name;
        return this;
    }

    public LaxioServerBuilder address(InetSocketAddress address) {
        Conditions.notNull(address, "address");
        if (!(networkServerBuilder instanceof LaxioServerBuilderNetworkServerBuilder)) {
            LOGGER.warn("Overriding NetworkServerBuilder to set address");
            networkServerBuilder(new LaxioServerBuilderNetworkServerBuilder());
        }

        this.address = address;
        return this;
    }

    public LaxioServerBuilder networkServerBuilder(NetworkServerBuilder networkServerBuilder) {
        Conditions.notNull(networkServerBuilder, "network server builder");
        this.networkServerBuilder = networkServerBuilder;
        return this;
    }

    public LaxioServerApplication build() {
        Conditions.notNull(name, "name");
        Conditions.notNull(networkServerBuilder, "network server builder");

        return new LaxioServerApplication(name, networkServerBuilder);
    }

    private final class LaxioServerBuilderNetworkServerBuilder implements NetworkServerBuilder {

        @Override
        public NetworkServer build(Application application) {
            return new LaxioServerNettyServer(application, address);
        }

    }

}
