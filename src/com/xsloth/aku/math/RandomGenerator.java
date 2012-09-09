package com.xsloth.aku.math;

import java.util.Random;

public class RandomGenerator {
	
	public static boolean generateAndGetResult(int range, float percentage){
		Random rand = new Random();
		int randValue = rand.nextInt(range) + 1;
		//System.out.println("In a range of: " + range + " I drawed: " + randValue + " So with a goal percentage of: " + percentage + " My percentage is: " + ((float)randValue/range));
		if(((float)randValue/range) < percentage)
			return true;
		else
			return false;
	}
}
