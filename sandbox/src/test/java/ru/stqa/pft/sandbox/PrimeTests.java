package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PrimeTests {
	@Test
	public void testPrime() {
		Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
	}

	@Test(enabled = false)
	public void testPrimeLong() {
		Long n = Long.valueOf(Integer.MAX_VALUE);
		Assert.assertTrue(Primes.isPrime(n));
	}

	@Test
	public void testNonPrime() {
		Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
	}
}
