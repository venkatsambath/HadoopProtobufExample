package com.github.venkat.protobuf.examples;

import com.github.venkat.protobuf.examples.HadoopRpcServiceProtos.HadoopRpcServiceProto;
import org.apache.hadoop.ipc.ProtocolInfo;

@ProtocolInfo(protocolName = "testProto", protocolVersion = 1)
public interface HadoopService extends HadoopRpcServiceProto.BlockingInterface {
}
