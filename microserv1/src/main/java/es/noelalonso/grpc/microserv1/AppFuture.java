package es.noelalonso.grpc.microserv1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListenableFuture;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceFutureStub;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Hello world!
 *
 */
public class AppFuture {
	static boolean _terminar = false;
	public static void main(String[] args) throws InterruptedException {

		System.out.println("Opening channel...");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
		GreetingServiceFutureStub stub = GreetingServiceGrpc.newFutureStub(channel);

		System.out.println("Calling gRPC...");
		final ListenableFuture<Greeting> response = stub.sayHello(Greeting.newBuilder().setText("NoeL").build());
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		response.addListener(()->{
			try {
				System.out.print("Respuesta: ");
				System.out.println(response.get().getText());
				executor.shutdown();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}, executor);

		executor.awaitTermination(30, TimeUnit.SECONDS);
		do {
			Thread.currentThread().sleep(1500);
		} while (!response.isDone());
		
		channel.shutdown();
		System.out.println("--FIN--");
	}
}
