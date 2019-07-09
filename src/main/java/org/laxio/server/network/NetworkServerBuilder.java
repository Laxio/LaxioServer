package org.laxio.server.network;

import org.laxio.Application;
import org.laxio.network.NetworkServer;

public interface NetworkServerBuilder {

    NetworkServer build(Application application);

}
