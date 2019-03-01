package es.noelalonso.grpc.microserv2.service.impl;

import org.lognet.springboot.grpc.GRpcService;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceImplBase;
import io.grpc.stub.StreamObserver;

@GRpcService
public class GreetingServiceImpl extends GreetingServiceImplBase {

	@Override
	public void sayHello(Greeting request, StreamObserver<Greeting> responseObserver) {
		Greeting response = Greeting.newBuilder()
		          .setText("Hello "+ request.getText() + "!")
		          .build();
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	
}
