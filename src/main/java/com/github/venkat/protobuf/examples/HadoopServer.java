package com.github.venkat.protobuf.examples;

import com.google.protobuf.BlockingService;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeys;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import com.github.venkat.protobuf.examples.HadoopRpcServiceProtos.EmptyRequestProto;
import com.github.venkat.protobuf.examples.HadoopRpcServiceProtos.EmptyResponseProto;
import org.apache.hadoop.net.NetUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HadoopServer {

    public final static String ADDRESS = "0.0.0.0";
    public final static int PORT = 10000;
    private static InetSocketAddress addr;
    private static Configuration conf;
    private static RPC.Server server;
    private final static int SLEEP_DURATION = 1000;


    public static class ServerImpl implements HadoopService
    {
        @Override
        public HadoopRpcServiceProtos.EmptyResponseProto ping(RpcController controller, EmptyRequestProto request) throws ServiceException {
            System.out.println("Hey someone sent me this   " +request.getMessage());
            return HadoopRpcServiceProtos.EmptyResponseProto.newBuilder().setMessage("Dude I read your message.. here you go").build();
        }
    }

    public static void main(String[] args) throws IOException, ServiceException {

        HadoopServer obj = new HadoopServer();
        obj.server_start();

    }

    public void server_start() throws IOException
    {
        conf = new Configuration();
        conf.setInt(CommonConfigurationKeys.IPC_MAXIMUM_DATA_LENGTH, 1024);
        conf.setBoolean(CommonConfigurationKeys.IPC_SERVER_LOG_SLOW_RPC, true);

        // Set RPC engine to the service we implemented
        RPC.setProtocolEngine(conf, HadoopService.class, ProtobufRpcEngine.class);

        // Create server side implementation
        ServerImpl serverImpl = new ServerImpl();

        // Register service implementation
        BlockingService service = HadoopRpcServiceProtos.HadoopRpcServiceProto
                .newReflectiveBlockingService(serverImpl);

        // Get RPC server for server side implementation
        server = new RPC.Builder(conf).setProtocol(HadoopService.class).setInstance(service).setBindAddress(ADDRESS).setPort(PORT).build();

        addr = NetUtils.getConnectAddress(server);

        server.start();
    }


}
