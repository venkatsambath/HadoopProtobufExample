package com.github.venkat.writables.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HadoopServiceClient {

    public static void main(String[] args) throws IOException {

        final ServerLogicInterface serverLogicInterface = (ServerLogicInterface) RPC.waitForProxy(ServerLogicInterface.class, 234345L, new InetSocketAddress(HadoopServer.SERVER_ADDRESS, HadoopServer.SERVER_PORT), new Configuration());

        String result = serverLogicInterface.hello("hyii");

        System.out.printf(result);

        RPC.stopProxy(serverLogicInterface);
    }
}
