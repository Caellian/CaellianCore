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

package com.github.caellian.core.processManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used for easy command execution.
 * <p>
 *
 * @author Caellian
 */
public class Command extends AbstractAction
{
	protected ArrayList<Object> argumentList = new ArrayList<>();

	/**
	 * @param arguments
	 * 		some of the variables used by this command.
	 * 		This is preferred in case an unwanted argument has to be given public access.
	 */
	public Command(Object... arguments)
	{
		argumentList.addAll(Arrays.asList(arguments));
	}

	public Command undo()
	{
		return this;
	}

	public Command redo()
	{
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.execute();
	}

	public Command execute(Object... arguments)
	{
		argumentList.addAll(Arrays.asList(arguments));
		return this;
	}
}
