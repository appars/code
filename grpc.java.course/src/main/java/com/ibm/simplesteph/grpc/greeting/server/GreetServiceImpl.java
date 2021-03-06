package com.ibm.simplesteph.grpc.greeting.server;

import com.proto.greet.GreetManyTimesRequest;
import com.proto.greet.GreetManyTimesResponse;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc.GreetServiceImplBase;
import com.proto.greet.Greeting;

import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceImplBase {

  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

    // Extract the field we need
    Greeting greeting = request.getGreeting();
    String FirstName = greeting.getFirstName();

    // Create the response
    String result = "Hello, " + FirstName + "!";
    GreetResponse response = GreetResponse.newBuilder()
        .setResult(result)
        .build();

    // Send the response
    responseObserver.onNext(response);

    // Complete the rpc call.
    responseObserver.onCompleted();

    // super.greet(request, responseObserver);
  }

  @Override
  public void greetManyTimes(GreetManyTimesRequest request,
      StreamObserver<GreetManyTimesResponse> responseObserver) {
    String firstName = request.getGreeting().getFirstName();
      try {
        for (int i = 0; i < 10; i++) {
          String result = "Hello, " + firstName + "response number: " + i;
          GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
              .setResult(result)
              .build();
          responseObserver.onNext(response);
          Thread.sleep(1000L);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        responseObserver.onCompleted();
      }
    }
}