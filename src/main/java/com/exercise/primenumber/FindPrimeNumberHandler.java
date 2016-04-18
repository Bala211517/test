package com.exercise.primenumber;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.exercise.primenumber.validation.ValidationResult;
import com.exercise.primenumber.validation.ValidatorService;

public class FindPrimeNumberHandler extends AbstractHandler{

	private Logger logger = LoggerFactory.getLogger(FindPrimeNumberHandler.class);

	@Autowired
	@Qualifier("validatorService")
	private ValidatorService validatorService;

	@Autowired
	@Qualifier("primeNumberService")
	private PrimeNumberService primeNumberService;

	@Autowired
	@Qualifier("executor")
	private ExecutorService executor;
	
	@Value("${primenumber-finder.timeout.in.seconds}")
	private int timeOut;

	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Recevied request");
		String parameter = request.getParameter("number");
		logger.info("Received parameter is {}", parameter);
		ValidationResult result = validatorService.validate(parameter);
		if(!result.isValid()){
			response.getWriter().write(result.getReason());
			response.flushBuffer();
			return;
		}
		
		boolean isPrime = false;
		Future<Boolean> futureTask = executor.submit(new PrimeNumberFindTask(primeNumberService, Integer.parseInt(parameter)));
		try {
			isPrime = futureTask.get(timeOut, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException e) {
			String reason = "Exception while executing task to find prime number for " + parameter;
			logger.error(reason, e);
			response.getWriter().write(reason);
			response.flushBuffer();
			return;
		} catch (TimeoutException e) {
			String reason = "Timedout while executing task to find prime number for " + parameter;
			logger.error(reason, e);
			response.getWriter().write(reason);
			response.flushBuffer();
			return;
		}

		if(isPrime){
			response.getWriter().write("Number " + parameter + " is prime");
		} else {
			response.getWriter().write("Number " + parameter + " is not prime");
		}
		response.flushBuffer();
	}
}
