package com.exercise.primenumber;

import java.util.concurrent.Callable;

public class PrimeNumberFindTask implements Callable<Boolean>{

	private PrimeNumberService primeNumberService;
	private int number;

	public PrimeNumberFindTask(PrimeNumberService primeNumberService, int number) {
		this.primeNumberService = primeNumberService;
		this.number = number;
	}
	
	public Boolean call() throws Exception {
		return primeNumberService.isPrime(number);
	}

}
