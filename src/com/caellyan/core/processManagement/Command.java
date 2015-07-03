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

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Caellian on 27.6.2015., at 15:09.
 */
public class Command extends AbstractAction
{
	private final ExecutionTimeline timeline;

	public Command(ExecutionTimeline executionTimeline)
	{
		timeline = executionTimeline;
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

	public Command execute()
	{
		timeline.commandExecuted(this);
		return this;
	}

	public ExecutionTimeline getTimeline()
	{
		return timeline;
	}
}
