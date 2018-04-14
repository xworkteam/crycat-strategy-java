package com.xteam.crycat.thrift;

import com.xteam.crycat.remote.RemoteServiceImpl;
import com.xteam.crycat.robot.RobotServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.*;

public class ThriftServer {

    public static void startServer() throws Exception{
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        RemoteService.Processor<RemoteServiceImpl> processor = new RemoteService.Processor<>(new RemoteServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);

        System.out.println("Thrift server started!");

        server.serve();
    }
}
