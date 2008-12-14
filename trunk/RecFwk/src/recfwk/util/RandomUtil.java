package recfwk.util;

import java.util.Random;

public class RandomUtil {
	private static Random random = new Random();

	public static float randomFloat(final double d, final double e) {
		return (float) (random.nextFloat() * (e - d) + d);
	}

	public static int randomInt(final int i, final int j) {
		return random.nextInt(j - i) + i;
	}
}
