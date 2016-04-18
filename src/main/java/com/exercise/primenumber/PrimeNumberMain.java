package com.exercise.primenumber;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrimeNumberMain {

	private static Logger logger = LoggerFactory.getLogger(PrimeNumberMain.class);

	@Value("${primenumber-finder.port}")
	private int port;

	@Autowired
	@Qualifier("findPrimeNumberHandler")
	private FindPrimeNumberHandler findPrimeNumberHandler;
	
	public  void init(){
		try{
		Server server = new Server(port);
		ContextHandler context = new ContextHandler();
		context.setContextPath("/findprime");
		context.setResourceBase(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setHandler(findPrimeNumberHandler);
		server.setHandler(context);
		server.start();
		} catch (Exception e){
			logger.error("Exception while starting web server {}", e);
		}
		
	}
	
	public static void main(String[] args) {

		try(ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("primenumber-finder-spring.xml")){
				
			PrimeNumberMain primeNumberMain = (PrimeNumberMain) springContext.getBean("primeNumberMain");
			primeNumberMain.init();
/*			Server server = new Server(port);
			ContextHandler context = new ContextHandler();
			context.setContextPath("/findprime");
			context.setResourceBase(".");
			context.setClassLoader(Thread.currentThread().getContextClassLoader());
			context.setHandler(findPrimeNumberHandler);
			server.setHandler(context);
			server.start();
*/		} catch (Exception e) {
			logger.error("Exception during server startup {}", e);
		}
	}
}