package com.github.venkat.protobuf.examples;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HadoopServiceClient {

    private static InetSocketAddress addr=new InetSocketAddress("192.168.0.2", 10000);
    private static Configuration conf= new Configuration();

    public static void main(String[] args) throws IOException, ServiceException {
        RPC.setProtocolEngine(conf, HadoopService.class,
                ProtobufRpcEngine.class);
        HadoopService client = RPC.getProxy(HadoopService.class, 0, addr, conf);
        HadoopRpcServiceProtos.EmptyResponseProto response = client.ping(null, HadoopRpcServiceProtos.EmptyRequestProto.newBuilder().setMessage("hiiii").build());

        System.out.println(response.getMessage());
    }
}
