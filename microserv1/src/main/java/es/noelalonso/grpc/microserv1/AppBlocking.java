package es.noelalonso.grpc.microserv1;

import java.util.Iterator;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class AppBlocking {
	static boolean _terminar = false;
	public static void main(String[] args) throws InterruptedException {

		System.out.println("Opening channel...");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
		GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

		callServer(stub);
		callServer(stub);
		callServer(stub);
		callServer(stub);
		
		channel.shutdown();
		System.out.println("--FIN--");
	}
	
	private static void callServer(GreetingServiceBlockingStub stub) {
		System.out.println("Calling gRPC...");
		long time = System.currentTimeMillis();
		Greeting response = stub.sayHello(Greeting.newBuilder().setText("NoeL").build());
		time = System.currentTimeMillis() - time;

		System.out.println("Respuesta: " + response.getText() + "(" + time + "ms)");
	}
}
