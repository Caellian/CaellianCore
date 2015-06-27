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

package com.caellyan.core.processManagement;

import java.util.ArrayList;

/**
 * Created by Caellian on 27.6.2015., at 16:30.
 * <p>
 * This is an alternative method of execution management. It is highly unstable
 * and is recommended only if you're using it as a layer to build you own execution management on top.
 * <p>
 * This method can also be used for more flexible execution management, but it might cause unpredicted
 * behaviour.
 */
public class ExecutionManagement
{
	private ArrayList<Command> commandList = new ArrayList<>();

	public void commandExecuted(Command command)
	{
		commandList.add(command);
	}

	public void redo(int position)
	{
		commandList.get(position).redo();
	}

	public void undo(int position)
	{
		commandList.get(position).undo();
	}
}
