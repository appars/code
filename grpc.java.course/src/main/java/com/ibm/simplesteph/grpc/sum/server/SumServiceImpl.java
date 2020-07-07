package com.ibm.simplesteph.grpc.sum.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc.GreetServiceImplBase;
import com.proto.greet.Greeting;
import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc.SumServiceImplBase;

import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceImplBase {

  @Override
  public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {

    // Extract the field we need
    Sum sum = request.getSum();
    int result = sum.getFirstNumber() + sum.getLastNumber();

    // Create the response
    SumResponse response = SumResponse.newBuilder()
        .setResult(result)
        .build();

    // Send the response
    responseObserver.onNext(response);

    // Complete the rpc call.
    responseObserver.onCompleted();

    // super.greet(request, responseObserver);
  }


}