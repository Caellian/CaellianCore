/*
 * Atlas Conscientia, artificial consciousness
 * Copyright (c) 2015 Skythees
 *
 * This project has been abandoned and thus you can redistribute it and/or modify
 * it under no terms.
 *
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * It might or is not in functioning state. Skythees isn't and will not be held
 * responsible for any consequences it might have on any software/hardware.
 */

package com.caellyan.core;

import java.util.HashMap;

/**
 * Created on 06.03.15.
 */
public class ArgumentHelper
{
	private HashMap<String, Action> argumentMap = new HashMap<>();

	public void addArgument(String argument, Action result)
	{
		argumentMap.put(argument, result);
	}

	public void removeArgument(String argument)
	{
		argumentMap.remove(argument);
	}

	public void clearAllArguments()
	{
		argumentMap.clear();
	}

	public void bind(ArgumentHelper argumentHelper)
	{
		argumentMap.putAll(argumentHelper.getAllArguments());
	}

	protected HashMap<String, Action> getAllArguments()
	{
		return argumentMap;
	}

	public boolean handleArguments(String[] args)
	{
		for (final String input : args)
		{
			if (argumentMap.containsKey(input))
			{
				return argumentMap.get(input).execute();
			}
		}
		return true;
	}

	public interface Action
	{
		boolean execute();
	}
}
