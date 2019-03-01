package es.noelalonso.grpc.microserv1;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Hello world!
 *
 */
public class AppClient {
	static boolean _terminar = false;
	public static void main(String[] args) throws InterruptedException {

		System.out.println("Opening channel...");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
		GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);

		System.out.println("Calling gRPC...");
		stub.sayHelloStream(Greeting.newBuilder().setText("NoeL").build(), new StreamObserver<Greeting>() {
			
			@Override
			public void onNext(Greeting value) {
				System.out.println("Respuesta: " + value.getText());
			}
			
			@Override
			public void onError(Throwable t) {
				System.out.println("Error: " + t.getMessage());
				AppClient._terminar = true;
			}
			
			@Override
			public void onCompleted() {
				System.out.println("Completed.");
				AppClient._terminar = true;
			}
		});

		do {
			Thread.currentThread().sleep(1500);
		} while (!AppClient._terminar);
		
		channel.shutdown();
		System.out.println("--FIN--");
	}
}
