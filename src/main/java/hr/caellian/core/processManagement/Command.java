/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.processManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used for easy command execution.
 * <p>
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
