package com.github.venkat.writables.example;


import org.apache.hadoop.ipc.VersionedProtocol;

public interface ServerLogicInterface extends VersionedProtocol {
    public abstract String hello(String name);
    public static final long versionID = 234345L;
}
