package es.noelalonso.grpc.microserv1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import es.noelalonso.gprc.msg.GrpcMessages.Greeting;
import es.noelalonso.gprc.svc.GreetingServiceGrpc;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import es.noelalonso.gprc.svc.GreetingServiceGrpc.GreetingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Hello world!
 *
 */
public class AppRest {
	static boolean _terminar = false;
	public static void main(String[] args) throws InterruptedException {

		
		System.out.println("Creating template...");
		RestTemplate restTemplate = new RestTemplate();
		
		callServer(restTemplate);
		callServer(restTemplate);
		callServer(restTemplate);
		
		System.out.println("--FIN--");
	}
	
	private static void callServer(RestTemplate restTemplate) {
		System.out.println("Calling REST...");
		long time = System.currentTimeMillis();
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/echo/Hola!", String.class);
		time = System.currentTimeMillis() - time;

		System.out.println("Respuesta: " + entity.getBody() + "(" + time + "ms)");
	}
}
