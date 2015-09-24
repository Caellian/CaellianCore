/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core;

import hr.caellian.core.processManagement.Command;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author Caellian
 */
public class ArgumentHelper
{
	private HashMap<String, Command> argumentMap    = new HashMap<>(0);
	private HashMap<String, Command> varArgumentMap = new HashMap<>(0);
	private HashMap<String, Type>    varTypeMap     = new HashMap<>(0);
	private String lastAdded;

	public ArgumentHelper addVariableArgument(String argument, Type inputType, Command result)
	{
		varArgumentMap.put(argument, result);
		varTypeMap.put(argument, inputType);
		lastAdded = argument;
		return this;
	}

	public ArgumentHelper addArgument(String argument, Command result)
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

	protected HashMap<String, Command> getAllArguments()
	{
		return argumentMap;
	}

	public void handleArguments(String[] args)
	{
		for (int argPos = 0; argPos < args.length; argPos++)
		{
			if (argumentMap.containsKey(args[argPos]))
			{
				argumentMap.get(args[argPos]).execute();
			}
			if (varArgumentMap.containsKey(args[argPos]))
			{
				try
				{
					if (varTypeMap.get(args[argPos]) == (Byte.class))
					{
						varArgumentMap.get(args[argPos]).execute(Byte.parseByte(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Short.class))
					{
						varArgumentMap.get(args[argPos]).execute(Short.parseShort(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Integer.class))
					{
						varArgumentMap.get(args[argPos]).execute(Integer.parseInt(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Long.class))
					{
						varArgumentMap.get(args[argPos]).execute(Long.parseLong(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Float.class))
					{
						varArgumentMap.get(args[argPos]).execute(Float.parseFloat(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Double.class))
					{
						varArgumentMap.get(args[argPos]).execute(Double.parseDouble(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Boolean.class))
					{
						varArgumentMap.get(args[argPos]).execute(Boolean.parseBoolean(args[argPos + 1]));
					}
					else if (varTypeMap.get(args[argPos]) == (Character.class))
					{
						varArgumentMap.get(args[argPos]).execute((args[argPos + 1]).charAt(0));
					}
					else if (varTypeMap.get(args[argPos]) == (String.class))
					{
						varArgumentMap.get(args[argPos]).execute(args[argPos + 1]);
					}
					else
					{
						System.err.println("Argument using unsupported type: " + varTypeMap.get(args[argPos]) + "(" + args[argPos] + ")");
					}
				} catch (Exception e)
				{
					System.err.println("Incorrect argument configuration at:" + argPos + "(" + args[argPos] + ")");
					e.printStackTrace();
				}
			}
		}
	}
}
