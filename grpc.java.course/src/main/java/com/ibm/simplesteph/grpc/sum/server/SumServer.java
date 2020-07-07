package com.ibm.simplesteph.grpc.sum.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class SumServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC");

    Server server = ServerBuilder.forPort(50051)
        .addService(new SumServiceImpl())
        .build();

    server.start();

    Runtime.getRuntime().addShutdownHook(new Thread( () -> {
      System.out.println("Received Shutdown Request");
      server.shutdown();
      System.out.println("Successfuly Stopped the server");
    } ));

    server.awaitTermination();
  }

}
