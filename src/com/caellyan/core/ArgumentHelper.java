/*
 * CaellianCore, universal library
 * Copyright (C) 2015 Caellian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.caellyan.core;

import java.util.HashMap;

/**
 * Created on 06.03.15.
 */
public class ArgumentHelper
{
	private HashMap<String, Action> argumentMap = new HashMap<>();
	private String lastAdded;

	public ArgumentHelper addArgument(String argument, Action result)
	{
		argumentMap.put(argument, result);
		lastAdded = argument;
		return this;
	}

	public ArgumentHelper addSynonim(String argumentNew, String argumentOld)
	{
		argumentMap.put(argumentNew, argumentMap.get(argumentOld));
		lastAdded = argumentNew;
		return this;
	}

	public ArgumentHelper addSynonim(String argumentNew)
	{
		argumentMap.put(argumentNew, argumentMap.get(lastAdded));
		lastAdded = argumentNew;
		return this;
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
