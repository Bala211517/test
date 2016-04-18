package com.exercise.primenumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * PrimeNumberServiceImpl offers the service to find whether the given number
 * is prime or not. The class PrimeNumberServiceImpl is thread safe.
 *
 */
@Component("primeNumberService")
public class PrimeNumberServiceImpl implements PrimeNumberService{

	private Logger logger = LoggerFactory.getLogger(PrimeNumberServiceImpl.class);
	
	public boolean isPrime(int number) {
		if(number == 0 || number == 1 || number == 2){
			logger.info("Number {} is not prime", number);
			return false;
		}
		
		boolean isPrime = true;
		int endNumber = number/2;
		for(int i = 2;i <= endNumber;i++){
			if(number % i == 0){
				isPrime = false;
				break;
			}
		}
		
		if(isPrime){
			logger.info("Number {} is prime", number);
			return true;
		} else {
			logger.info("Number {} is not prime", number);
			return false;
		}
	}

}
