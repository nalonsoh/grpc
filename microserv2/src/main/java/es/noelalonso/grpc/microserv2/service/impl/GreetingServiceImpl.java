package es.noelalonso.grpc.microserv2.service.impl;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceImplBase;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceImplBase {

	@Override
	public void sayHello(Greeting request, StreamObserver<Greeting> responseObserver) {
		Greeting response = Greeting.newBuilder()
		          .setText("Hello "+ request.getText() + "!")
		          .build();
		responseObserver.onNext(response);
		
		responseObserver.onCompleted();
	}

	@Override
	public void sayHelloStream(Greeting request, StreamObserver<Greeting> responseObserver) {
		Greeting response = Greeting.newBuilder()
		          .setText("Hello "+ request.getText() + "!")
		          .build();
		responseObserver.onNext(response);
		
		response = Greeting.newBuilder()
		          .setText("Hello "+ request.getText() + "!!")
		          .build();
		responseObserver.onNext(response);
		
		response = Greeting.newBuilder()
				.setText("Hello "+ request.getText() + "!!!")
				.build();
		responseObserver.onNext(response);
		
		responseObserver.onCompleted();
	}
	
	

	
}
