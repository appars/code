package com.ibm.simplesteph.grpc.sum.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {

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
    SumServiceGrpc.SumServiceBlockingStub sumClient = SumServiceGrpc.newBlockingStub(channel);

    // created protocol buffer greeting message
    Sum sum = Sum.newBuilder()
        .setFirstNumber(3)
        .setLastNumber(10)
        .build();

    // created greet Request with greeting
    SumRequest sumRequest = SumRequest.newBuilder()
        .setSum(sum)
        .build();

    // call gRPC and get the Greet response.
    SumResponse sumResponse = sumClient.sum(sumRequest);
    System.out.println("Received Response from Server: " + sumResponse.getResult());

    // do something
    System.out.println("Shutingdown the channel");
    channel.shutdown();

  }

}