package com.isnotworking.recfwk.util;

import java.util.Random;

/**
 * Provides helpers for common random/statistics functions
 * 
 * @author ricardocabral
 * 
 */
public class RandomUtil {
	private static Random random = new Random();

	/**
	 * random float between two numbers
	 * 
	 * @param d
	 *            lower limit
	 * @param e
	 *            upper limit
	 * @return random float on the given closed interval
	 */
	public static float randomFloat(final double d, final double e) {
		return (float) (random.nextFloat() * (e - d) + d);
	}

	/**
	 * random integer between two numbers
	 * 
	 * @param i
	 *            lower limit
	 * @param j
	 *            upper limit
	 * @return random int on the given closed interval
	 */
	public static int randomInt(final int i, final int j) {
		return random.nextInt(j - i) + i;
	}
}
