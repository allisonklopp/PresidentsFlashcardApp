/**
 * 
 */
package com.catbird.presidents;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author allisonklopp
 *
 */
public class Utils {
	/**
	 * TODO
	 * @param list
	 */
	public static void randomizeList(List<String> list)
	{
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
	}
	
	/**
	 * TODO
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
