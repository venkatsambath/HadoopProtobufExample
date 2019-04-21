package com.github.venkat.writables.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

public class HadoopServer {

    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 20000;

    public static void main(String[] args) throws IOException {
        final RPC.Server server= RPC.getServer(new HadoopService(),SERVER_ADDRESS,SERVER_PORT,new Configuration());
        server.start();
    }
}
