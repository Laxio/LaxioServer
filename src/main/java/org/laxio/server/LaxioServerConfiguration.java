package org.laxio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioServerConfiguration implements ServerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioServerConfiguration.class);

    private boolean authenticated;
    private boolean encrypted;

    public LaxioServerConfiguration() {
        this.authenticated = true;
        this.encrypted = true;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated && !encrypted) {
            setEncrypted(true);
            LOGGER.debug("Forcefully enabling encryption");
        }

        this.authenticated = authenticated;
    }

    @Override
    public boolean isEncrypted() {
        return encrypted;
    }

    @Override
    public void setEncrypted(boolean encrypted) {
        if (!encrypted && authenticated) {
            throw new IllegalStateException("Cannot disable encryption with authentication enabled");
        }

        this.encrypted = encrypted;
    }

}
