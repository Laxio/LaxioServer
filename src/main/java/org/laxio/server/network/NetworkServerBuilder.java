package org.laxio.server.network;

import org.laxio.LaxioApplication;
import org.laxio.network.NetworkServer;

public interface NetworkServerBuilder {

    NetworkServer build(LaxioApplication application);

}
