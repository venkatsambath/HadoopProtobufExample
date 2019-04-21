package com.github.venkat.writables.example;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class HadoopService implements ServerLogicInterface {
    public String hello(String name)
    {
        return "Dude Someone called me.. Here you go  " + name;
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return HadoopService.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }
}
