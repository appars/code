package com.ibm.simplesteph.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

  public static void main(String[] args) {
    System.out.println("Hello I'm a gRPC client");

    System.out.println("Creating stub");
    ManagedChannel  channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

    // Dummy Service
    // DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
    // non blocking
    // DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);


    // Created a greet service client (blocking synchronus)
    GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

    // created protocol buffer greeting message
    Greeting greeting = Greeting.newBuilder()
        .setFirstName("Apparsamy")
        .setLastName("Perumal")
        .build();

    // created greet Request with greeting
    GreetRequest greetRequest = GreetRequest.newBuilder()
        .setGreeting(greeting)
        .build();

    // call gRPC and get the Greet response.
    GreetResponse greetResponse = greetClient.greet(greetRequest);

    System.out.println(greetResponse.getResult());

    // do something
    System.out.println("Shutingdown the channel");
    channel.shutdown();

  }

}