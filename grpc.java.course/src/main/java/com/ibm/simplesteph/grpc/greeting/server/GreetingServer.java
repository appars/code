package com.ibm.simplesteph.grpc.greeting.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GreetingServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC");

    Server server = ServerBuilder.forPort(50051)
        .addService(new GreetServiceImpl())
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
