package com.github.caellian.core.util;

import java.util.List;
import java.util.Random;

/**
 * @author Caellian
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
