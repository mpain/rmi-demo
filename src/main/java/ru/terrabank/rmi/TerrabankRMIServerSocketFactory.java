package ru.terrabank.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;

/**
 * User: sergey.samoylov@gmail.com
 * Date: 8/17/11
 * Time: 10:35 PM
 */
public class TerrabankRMIServerSocketFactory implements Serializable, RMIServerSocketFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private int timeoutInMilliseconds;

    public TerrabankRMIServerSocketFactory(Integer timeoutInMilliseconds) {
        this.timeoutInMilliseconds = (timeoutInMilliseconds != null) ? timeoutInMilliseconds.intValue() : 1000;
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        ServerSocket socket = RMISocketFactory.getDefaultSocketFactory().createServerSocket(port);
        socket.setSoTimeout(timeoutInMilliseconds);

        log.info("Creating a server socket having {} milliseconds timeout", timeoutInMilliseconds);
        return socket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerrabankRMIServerSocketFactory that = (TerrabankRMIServerSocketFactory) o;

        if (timeoutInMilliseconds != that.timeoutInMilliseconds) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return timeoutInMilliseconds;
    }
}
