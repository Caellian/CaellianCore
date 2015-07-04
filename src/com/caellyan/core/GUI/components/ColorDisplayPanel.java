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

package com.caellyan.core.GUI.components;

import com.caellyan.core.processManagement.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Caellian on 27.6.2015., at 23:43.
 */
public class ColorDisplayPanel extends JPanel
{
	final String csString;
	ColorDisplayPanel self;
	Command executeAfter = null;

	public ColorDisplayPanel(String title)
	{
		super(null);
		self = this;
		csString = title;
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				super.mouseClicked(e);
				self.setBackground(JColorChooser.showDialog(self, csString, null));
				if (executeAfter != null)
				{
					executeAfter.execute();
				}
			}
		});
	}

	public ColorDisplayPanel executeCommandAfter(Command command)
	{
		executeAfter = command;
		return this;
	}
}
