package com.caellyan.core.util;

import java.util.List;
import java.util.Random;

/**
 * Created by Caellian on 6.7.2015., at 3:02.
 */
public class VariableManipulator
{
	public static Character[] from(char[] chars)
	{
		Character[] result = new Character[chars.length];
		for (int count = 0; count < chars.length; count++)
		{
			result[count] = chars[count];
		}
		return result;
	}

	public static Object randomOf(List list)
	{
		Object[] newList = list.toArray();
		return newList[new Random().nextInt(newList.length)];
	}
}
