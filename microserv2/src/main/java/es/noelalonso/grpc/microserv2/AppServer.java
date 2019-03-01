package es.noelalonso.grpc.microserv2;

import java.io.IOException;

import es.noelalonso.grpc.microserv2.service.impl.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Hello world!
 *
 */
public class AppServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting gRPC server...");

		Server server = ServerBuilder.forPort(8080).addService(new GreetingServiceImpl()).build();

		server.start();
		System.out.println("RPC server started.");
		server.awaitTermination();
		System.out.println("--END--");
	}
}
