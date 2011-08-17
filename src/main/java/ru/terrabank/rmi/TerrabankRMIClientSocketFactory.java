package ru.terrabank.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMISocketFactory;

/**
 * User: sergey.samoylov@gmail.com
 * Date: 8/17/11
 * Time: 10:28 PM
 */
public class TerrabankRMIClientSocketFactory implements Serializable, RMIClientSocketFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private int timeoutInMilliseconds;

    public TerrabankRMIClientSocketFactory(Integer timeoutInMilliseconds) {
        this.timeoutInMilliseconds = (timeoutInMilliseconds != null) ? timeoutInMilliseconds.intValue() : 1000;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        Socket socket = RMISocketFactory.getDefaultSocketFactory().createSocket(host, port);
        socket.setSoTimeout(timeoutInMilliseconds);

        log.info("Creating a client socket having {} milliseconds timeout", timeoutInMilliseconds);
        return socket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerrabankRMIClientSocketFactory that = (TerrabankRMIClientSocketFactory) o;

        if (timeoutInMilliseconds != that.timeoutInMilliseconds) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return timeoutInMilliseconds;
    }
}
