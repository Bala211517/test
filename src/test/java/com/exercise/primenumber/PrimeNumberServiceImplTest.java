package com.exercise.primenumber;

import org.junit.Assert;
import org.junit.Test;


public class PrimeNumberServiceImplTest{

	private PrimeNumberService numberService = new PrimeNumberServiceImpl();

	@Test
	public void testZeroOneAndTwoNumber(){
		boolean isPrime = numberService.isPrime(0);
		Assert.assertFalse(isPrime);

		isPrime = numberService.isPrime(1);
		Assert.assertFalse(isPrime);

		isPrime = numberService.isPrime(2);
		Assert.assertFalse(isPrime);
	}
	
	@Test
	public void testValidPrimeNumber(){
		boolean isPrime = numberService.isPrime(3);
		Assert.assertTrue(isPrime);
		
		isPrime = numberService.isPrime(7);
		Assert.assertTrue(isPrime);
		
		isPrime = numberService.isPrime(1021);
		Assert.assertTrue(isPrime);
		
		isPrime = numberService.isPrime(Integer.MAX_VALUE);
		Assert.assertTrue(isPrime);
	}
	
	@Test
	public void testNotPrimeNumber(){
		boolean isPrime = numberService.isPrime(4);
		Assert.assertFalse(isPrime);
		
		isPrime = numberService.isPrime(56);
		Assert.assertFalse(isPrime);
		
		isPrime = numberService.isPrime(9082);
		Assert.assertFalse(isPrime);
		
		isPrime = numberService.isPrime(Integer.MAX_VALUE - 1);
		Assert.assertFalse(isPrime);
	}
}
